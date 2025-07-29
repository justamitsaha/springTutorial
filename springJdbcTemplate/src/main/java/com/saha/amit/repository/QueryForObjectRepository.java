package com.saha.amit.repository;

import com.saha.amit.dto.ProductDto;
import com.saha.amit.mapper.ProductDtoRowMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QueryForObjectRepository {

    private final JdbcTemplate jdbcTemplate;

    Log log = LogFactory.getLog(ProductRepository.class);

    @Autowired
    public QueryForObjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getProductCount(){
        String sql = "SELECT COUNT(*) FROM Product";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public String getProductNameByUuid(int productUuid) {
        String sql = "SELECT name FROM Product WHERE product_uuid = ?";
        return jdbcTemplate.queryForObject(sql, String.class,productUuid);
    }

    public ProductDto getProductByUuid(int productUuid) {
        String sql = "SELECT * FROM Product WHERE product_uuid = ?";
        return jdbcTemplate.queryForObject(sql, new ProductDtoRowMapper(), productUuid);
    }

}
