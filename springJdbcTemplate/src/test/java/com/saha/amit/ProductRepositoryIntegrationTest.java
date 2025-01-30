package com.saha.amit;

import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProductDto;
import com.saha.amit.repository.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql(scripts = "classpath:schema/test-schema.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ProductRepository productRepository;

    Log log = LogFactory.getLog(ProductRepositoryIntegrationTest.class);

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository(jdbcTemplate);
        jdbcTemplate.update("INSERT INTO Product (name, price) VALUES (?, ?)", "iPhone", 99999.99);
        jdbcTemplate.update("INSERT INTO Product (name, price) VALUES (?, ?)", "Google Pixel", 74999.99);
    }

    @Test
    void testAddProductWithCategories() {
        // Arrange
        String productName = "Smartphone";
        double price = 599.99;

        // Fetch category IDs
        List<Long> categoryIds = jdbcTemplate.query(
                "SELECT category_uuid FROM Category WHERE name IN ('Electronics', 'Home Appliances')",
                (rs, rowNum) -> rs.getLong("category_uuid")
        );
        System.out.println(categoryIds);

        // Act
        Long productUuid = productRepository.addProductWithCategories(productName, price, categoryIds);

        // Assert: Verify product insertion
        Map<String, Object> product = jdbcTemplate.queryForMap(
                "SELECT * FROM Product WHERE product_uuid = ?",
                productUuid
        );
        assertEquals(productName, product.get("name"));
        assertEquals(price, product.get("price"));
        assertNotNull(product.get("created_date"));
        assertNotNull(product.get("modified_date"));

        // Assert: Verify product-category relationship
        List<Long> associatedCategories = jdbcTemplate.query(
                "SELECT category_id FROM product_category WHERE product_id = ?",
                (rs, rowNum) -> rs.getLong("category_id"),
                productUuid
        );
        assertEquals(categoryIds.size(), associatedCategories.size());
        assertTrue(associatedCategories.containsAll(categoryIds));
    }

    @Test
    void testCreateOrders(){
        ProductDto productDto = new ProductDto();
        productDto.setName("Product1");
        productDto.setProductUuid(1L);

        ProductDto productDto1 = new ProductDto();
        productDto1.setName("Product2");
        productDto1.setProductUuid(2L);

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(String.valueOf(new Random().nextInt(1000,9999)));
        var products = Map.of(productDto.getProductUuid(),productDto, productDto1.getProductUuid(), productDto1);
        orderDto.setProducts(products);

        assertTrue(productRepository.createOrders(orderDto, 1)> 0);
    }

    @Test
    void testCreateOrderWithNoProduct(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(String.valueOf(new Random().nextInt(1000,9999)));

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.createOrders(orderDto, 1);
        });
        assertEquals(throwable.getMessage(),AppConstants.NO_PRODUCT_EXCEPTION_MESSAGE);
    }

    @Test
    void testCreateOrderWithInvalidProduct(){
        ProductDto productDto = new ProductDto();
        productDto.setName("Product1");
        productDto.setProductUuid(3L);

        ProductDto productDto1 = new ProductDto();
        productDto1.setName("Product2");
        productDto1.setProductUuid(5L);

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(String.valueOf(new Random().nextInt(1000,9999)));
        var products = Map.of(productDto.getProductUuid(),productDto, productDto1.getProductUuid(), productDto1);
        orderDto.setProducts(products);

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.createOrders(orderDto, 1);
        });
        assertEquals(throwable.getMessage(),AppConstants.INVALID_PRODUCT_EXCEPTION_MESSAGE);
    }
}
