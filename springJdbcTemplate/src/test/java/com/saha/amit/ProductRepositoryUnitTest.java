package com.saha.amit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProductRepositoryUnitTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productRepository = new ProductRepository(jdbcTemplate);
    }

    @Test
    void testAddProductWithCategories() throws Exception {
        // Arrange
        String productName = "Test Product";
        double price = 99.99;
        List<Long> categoryIds = Arrays.asList(1L, 2L, 3L);
        long generatedProductId = 123L;

        // Mock GeneratedKeyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> keyMap = Map.of("GENERATED_KEY", generatedProductId);
        keyHolder.getKeyList().add(keyMap);

        // Mock PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mock(ResultSet.class));

        // Mock JdbcTemplate behavior for inserting product
        doAnswer(invocation -> {
            PreparedStatementCreator creator = invocation.getArgument(0);
            KeyHolder providedKeyHolder = invocation.getArgument(1);
            providedKeyHolder.getKeyList().add(keyMap);
            return 1; // Simulate 1 row affected
        }).when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

        // Mock JdbcTemplate behavior for inserting categories
        when(jdbcTemplate.update(eq("INSERT INTO product_category (product_id, category_id) VALUES (?, ?)"), anyLong(), anyLong()))
                .thenReturn(1); // Simulate 1 row affected for each insertion

        // Act
        ProductRepository productRepository = new ProductRepository(jdbcTemplate);
        Long productId = productRepository.addProductWithCategories(productName, price, categoryIds);

        // Assert
        assertNotNull(productId);
        assertEquals(generatedProductId, productId);

        // Verify product insertion
        verify(jdbcTemplate, times(1)).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

        // Verify category insertion
        verify(jdbcTemplate, times(categoryIds.size())).update(
                eq("INSERT INTO product_category (product_id, category_id) VALUES (?, ?)"),
                eq(generatedProductId),
                anyLong()
        );
    }

}
