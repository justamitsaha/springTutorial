package com.saha.amit.repository;

import com.saha.amit.AppConstants;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CreateQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    Log log = LogFactory.getLog(CreateQueryRepository.class);

    public CreateQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Long insertCustomer(CustomerDto customerDto) {
        // Insert into Profile table
        String profileSql = "INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

        // Retrieve the generated profile_uuid
        Long profileUuid = Objects.requireNonNull(keyHolder.getKey()).longValue();
        log.info("Profile UUID" +profileUuid);

        // Insert into Customer table using the generated profile_uuid
        String customerSql = "INSERT INTO Customer (customer_uuid, customer_name) VALUES (?, ?)";
        int customerUUid = jdbcTemplate.update(customerSql, profileUuid, customerDto.getName());
        log.info("Customer UUID" +customerUUid);
        return (long) profileUuid;
    }

    @Transactional
    public Long addProductWithCategories(String productName, double price, List<Long> categoryIds) {
        // Insert into Product table
        String productSql = "INSERT INTO Product (name, price, created_date, modified_date) VALUES (?, ?, CURRENT_DATE, CURRENT_TIMESTAMP)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(productSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, productName);
            ps.setDouble(2, price);
            return ps;
        }, keyHolder);

        // Retrieve the generated product_uuid
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("PRODUCT_UUID")) {
            throw new IllegalStateException("Failed to retrieve generated key for PRODUCT_UUID");
        }
        long productUuid = ((Number) keys.get("PRODUCT_UUID")).longValue();

        /* Inserting in multiple queries
        String joinTableSql = "INSERT INTO product_category (product_id, category_id) VALUES (?, ?)";
        for (Long categoryId : categoryIds) {
            jdbcTemplate.update(joinTableSql, productUuid, categoryId);
        }*/

        // Batch insert into product_category join table
        String joinTableSql = "INSERT INTO product_category (product_id, category_id) VALUES (?, ?)";
        jdbcTemplate.batchUpdate(joinTableSql, categoryIds, categoryIds.size(),
                (ps, categoryId) -> {
                    ps.setLong(1, productUuid);
                    ps.setLong(2, categoryId);
                });

        return productUuid;
    }

    @Transactional
    public Integer createOrders(OrderDto orderDto, long customerId) {
        String productCheckCountSql = "SELECT count(*) FROM PRODUCT WHERE PRODUCT_UUID IN (:ids)";

        // Extract product IDs from OrderDto
        List<Long> productIdList = new ArrayList<>();
        if (null != orderDto && null != orderDto.getProducts() && !orderDto.getProducts().isEmpty())
            orderDto.getProducts().forEach(product -> {
                productIdList.add(product.getProductUuid());
            });
        else
            throw new IllegalArgumentException(AppConstants.NO_PRODUCT_EXCEPTION_MESSAGE);

        // Use NamedParameterJdbcTemplate
        SqlParameterSource parameters = new MapSqlParameterSource("ids", productIdList);
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        // Execute the query
        Integer count = namedJdbcTemplate.queryForObject(productCheckCountSql, parameters, Integer.class);
        if (!(null != count && count == productIdList.size())){
            log.info("Product not found"+ count);
            throw new IllegalArgumentException(AppConstants.INVALID_PRODUCT_EXCEPTION_MESSAGE);
        }


        String insertToOder = "INSERT INTO Orders (order_uuid, order_number, customer_id) values (?, ? , ?)";

        return count;
    }
}
