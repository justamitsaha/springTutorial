package com.saha.amit.repository;

import com.saha.amit.dto.ProductDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.mapper.ProductDtoRowMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimpleQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    Log log = LogFactory.getLog(ProductRepository.class);

    @Autowired
    public SimpleQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getProductCount() {
        String sql = "SELECT COUNT(*) FROM Product";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public String getProductNameByUuid(int productUuid) {
        String sql = "SELECT name FROM Product WHERE product_uuid = ?";
        return jdbcTemplate.queryForObject(sql, String.class, productUuid);
    }

    public ProductDto getProductByUuid(int productUuid) {
        String sql = "SELECT * FROM Product WHERE product_uuid = ?";
        return jdbcTemplate.queryForObject(sql, new ProductDtoRowMapper(), productUuid);
    }

    public Integer productInProductId(List<Long> productIdList) {
        String productCheckCountSql = "SELECT count(*) FROM PRODUCT WHERE PRODUCT_UUID IN (:ids)";

        SqlParameterSource parameters = new MapSqlParameterSource("ids", productIdList);
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        Integer count = namedJdbcTemplate.queryForObject(productCheckCountSql, parameters, Integer.class);

        log.info("Product count " + count);
        return count;
    }

    public ProfileDto findCustomerProfileById(Long profileUuid) {
        String sql = "SELECT * FROM Profile WHERE profile_uuid = ?";
        return jdbcTemplate.query(sql, rs -> {
            ProfileDto profileDto = new ProfileDto();
            profileDto.setProfileUuid(rs.getLong("profile_uuid"));
            profileDto.setEmail(rs.getString("email"));
            profileDto.setName(rs.getString("name"));
            profileDto.setPhoneNumber(rs.getString("phone_number"));
            profileDto.setStreet(rs.getString("street"));
            profileDto.setCity(rs.getString("city"));
            profileDto.setState(rs.getString("state"));
            profileDto.setZipCode(rs.getString("zip_code"));
            return profileDto;
        }, profileUuid);
    }

    public List<ProductDto> getAllProducts(){
        String sql = "SELECT * FROM Product";
        List<ProductDto> products = jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductDto product = new ProductDto();
            product.setProductUuid(rs.getLong("product_uuid"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setCreatedDate(rs.getDate("created_date").toLocalDate());
            product.setModifiedDate(rs.getDate("modified_date").toLocalDate().atStartOfDay());
            return product;
        });

        products.forEach(log::info);
        return products;
    }

}
