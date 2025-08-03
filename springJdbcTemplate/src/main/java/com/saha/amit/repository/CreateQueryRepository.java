package com.saha.amit.repository;

import com.saha.amit.AppConstants;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Repository
public class CreateQueryRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CreateQueryRepository.class);

    public CreateQueryRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Transactional
    public Long insertCustomer(CustomerDto customerDto) {
        try {
            // Step 1: Insert into Profile
            String profileSql = "INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(profileSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, customerDto.getEmail());
                ps.setString(2, customerDto.getName());
                ps.setString(3, customerDto.getPhoneNumber());
                ps.setString(4, customerDto.getStreet());
                ps.setString(5, customerDto.getCity());
                ps.setString(6, customerDto.getState());
                ps.setString(7, customerDto.getZipCode());
                return ps;
            }, keyHolder);

            // Step 2: Extract generated key
            Number key = keyHolder.getKey();
            if (key == null) {
                logger.error("Profile UUID was not generated. Aborting.");
                throw new IllegalStateException("Failed to generate Profile UUID");
            }
            Long profileUuid = key.longValue();
            logger.info("Generated Profile UUID: {}", profileUuid);

            // Step 3: Insert into Customer
            String customerSql = "INSERT INTO Customer (customer_uuid, customer_name) VALUES (?, ?)";
            jdbcTemplate.update(customerSql, profileUuid, customerDto.getName());

            return profileUuid;
        } catch (DataAccessException dae) {
            logger.error("Database error occurred while inserting customer: {}", dae.getMessage(), dae);
            throw new RuntimeException("Error while inserting customer data", dae);
        } catch (Exception ex) {
            logger.error("Unexpected error occurred while inserting customer: {}", ex.getMessage(), ex);
            throw ex;  // or wrap in a custom exception
        }
    }


    /**
     * Adds a new product and links it to multiple categories.
     * Operations performed:
     * 1. Inserts the product into the `Product` table with the provided name and price.
     * 2. Automatically sets `created_date` and `modified_date`.
     * 3. Retrieves the generated product UUID (primary key).
     * 4. Inserts entries into the `product_category` join table to associate the product with each given category ID.
     *
     * @param productName Name of the product.
     * @param price       Price of the product.
     * @param categoryIds List of category IDs to associate with the product.
     * @return The generated product UUID.
     */
    @Transactional
    public Long addProductWithCategories(String productName, double price, List<Integer> categoryIds) {
        // 1. Insert into Product table
        String productSql = """
                    INSERT INTO Product (name, price, created_date, modified_date)
                    VALUES (?, ?, CURRENT_DATE, CURRENT_TIMESTAMP)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(productSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, productName);
            ps.setDouble(2, price);
            return ps;
        }, keyHolder);

        // 2. Retrieve the generated product_uuid (handle both cases: getKey() and getKeys())
        Map<String, Object> keys = keyHolder.getKeys();
        assert keys != null;
        logger.info("Product details: {}", keys);
        if (!keys.containsKey("PRODUCT_UUID")) {
            throw new IllegalStateException("Failed to retrieve generated key for PRODUCT_UUID");
        }
        long productUuid = ((Number) keys.get("PRODUCT_UUID")).longValue();

        // 3. Optional: Check for empty category list to avoid unnecessary SQL batch
        if (categoryIds != null && !categoryIds.isEmpty()) {
            String joinSql = "INSERT INTO product_category (product_id, category_id) VALUES (?, ?)";
            jdbcTemplate.batchUpdate(joinSql, categoryIds, categoryIds.size(), (ps, categoryId) -> {
                logger.info("Adding category id {} to product UUID {}", categoryId, productUuid);
                ps.setLong(1, productUuid);
                ps.setLong(2, categoryId);
            });
        } else {
            logger.error("Please provide at least one product category id, {}", categoryIds);
            throw new IllegalStateException("Please provide at least one product category id");
        }

        return productUuid;
    }

    @Transactional
    public Long addProductWithCategories2(ProductDto productDto) {
        productDto.setCreatedDate(LocalDate.now());
        productDto.setModifiedDate(LocalDateTime.now());
        // 1. Insert product and get generated product_uuid
        String insertProductSql = """
            INSERT INTO Product (name, price, created_date, modified_date)
            VALUES (:name, :price, :createdDate, :modifiedDate)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(productDto);

        namedJdbcTemplate.update(insertProductSql, paramSource, keyHolder, new String[] { "product_uuid" });
        Long productUuid = keyHolder.getKey().longValue();

        // 2. Validate at least one category
        if (productDto.getCategoryIds() == null || productDto.getCategoryIds() .isEmpty()) {
            throw new IllegalArgumentException("At least one category ID must be provided.");
        }

        // 3. Prepare batch insert for product_category
        String insertJoinSql = "INSERT INTO product_category (product_id, category_id) VALUES (:productId, :categoryId)";
        List<Map<String, Object>> batchValues = new ArrayList<>();

        for (Integer categoryId : productDto.getCategoryIds() ) {
            Map<String, Object> map = new HashMap<>();
            map.put("productId", productUuid);
            map.put("categoryId", categoryId);
            batchValues.add(map);
        }

        namedJdbcTemplate.batchUpdate(insertJoinSql, batchValues.toArray(new Map[0]));

        return productUuid;
    }

    /**
     * Creates a new order for a given customer.
     * This method performs the following steps:
     * 1. Validates that all provided product UUIDs exist in the database.
     * 2. Generates a new order UUID and saves the order details.
     * 3. Creates entries in the Order_Product join table for each associated product.
     * 4. Creates a default payment record with status 'PROCESSING' linked to the order.
     *
     * @param orderDto   the order details, including product IDs and optional order number
     * @param customerId the ID of the customer placing the order
     * @return the generated order UUID
     * @throws IllegalArgumentException if product IDs are missing or invalid
     */
    @Transactional
    public String createOrders(OrderDto orderDto, long customerId) {
        String productCheckCountSql = "SELECT * FROM Product WHERE product_uuid IN (:ids)";

        // Step 1: Extract product IDs from DTO
        if (null != orderDto.getProducts() && orderDto.getProductIds().isEmpty()) {
            logger.error("Product UUID is empty");
            throw new IllegalArgumentException(AppConstants.NO_PRODUCT_EXCEPTION_MESSAGE);
        }

        // Step 2: Validate product IDs exist in DB
        SqlParameterSource parameters = new MapSqlParameterSource("ids", orderDto.getProductIds());
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        List<ProductDto> products = namedJdbcTemplate.query(productCheckCountSql, parameters, (rs, rowNum) -> {
            ProductDto product = new ProductDto();
            product.setProductUuid(rs.getLong("product_uuid"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setCreatedDate(rs.getDate("created_date").toLocalDate());
            product.setModifiedDate(rs.getDate("modified_date").toLocalDate().atStartOfDay());
            return product;
        });
        orderDto.setProducts(products);
        logger.info("Products for which order is created: {}", products);
        int count = products.size();

        if (count != orderDto.getProductIds().size()) {
            logger.error("Some products not found. Expected: {}, Found: {}", orderDto.getProductIds().size(), count);
            throw new IllegalArgumentException(AppConstants.INVALID_PRODUCT_EXCEPTION_MESSAGE);
        }
        logger.info("Product UUID: {}", products);

        // Step 3: Generate order UUID and insert into Orders
        String orderUuid = UUID.randomUUID().toString();
        String orderNumber = Optional.ofNullable(orderDto.getOrderNumber()).orElse(UUID.randomUUID().toString().substring(0, 8)); // fallback

        String insertOrderSql = "INSERT INTO Orders (order_uuid, order_number, customer_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertOrderSql, orderUuid, orderNumber, customerId);
        logger.info("Order UUID: {}", orderUuid);

        // Step 4: Insert into Order_Product
        String insertOrderProductSql = "INSERT INTO Order_Product (order_uuid, product_uuid) VALUES (?, ?)";
        for (Integer productId : orderDto.getProductIds()) {
            jdbcTemplate.update(insertOrderProductSql, orderUuid, productId);
        }

        logger.info("Inserted in Order_Product table");

        // Step 5: Insert default payment (if needed)
        // You can adjust logic here to derive status dynamically
        String insertPaymentSql = "INSERT INTO Payment (payment_status, order_id) VALUES (?, ?)";
        jdbcTemplate.update(insertPaymentSql, "PROCESSING", orderUuid);

        // Step 6: Return order UUID or ID for tracking
        return orderUuid;
    }

}
