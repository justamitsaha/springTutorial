package com.saha.amit.mapper;

import com.saha.amit.dto.ProductDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductDtoRowMapper implements RowMapper<ProductDto> {

    @Override
    public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductDto dto = new ProductDto();
        dto.setProductUuid(rs.getLong("product_uuid"));
        dto.setName(rs.getString("name"));
        dto.setPrice(rs.getDouble("price"));

        // created_date is DATE, so use getObject with LocalDate
        dto.setCreatedDate(rs.getObject("created_date", LocalDate.class));

        // modified_date is TIMESTAMP, so use getObject with LocalDateTime
        dto.setModifiedDate(rs.getObject("modified_date", LocalDateTime.class));

        // categoryIds not available in DB directly, leaving it null/empty
        dto.setCategoryIds(null);

        return dto;
    }
}

