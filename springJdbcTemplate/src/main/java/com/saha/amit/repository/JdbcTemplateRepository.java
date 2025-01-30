package com.saha.amit.repository;

import com.saha.amit.dto.ProfileDto;
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
public class JdbcTemplateRepository {


    private final JdbcTemplate jdbcTemplate;

    Log log = LogFactory.getLog(JdbcTemplateRepository.class);

    @Autowired
    public JdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer productCount() {
        String sql = "SELECT COUNT(*) FROM Product";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

        log.info("Product count " + count);
        return count;
    }

    public Integer productCountInCategory(int categoryId) {
        String sql = """
                SELECT COUNT(pc.product_id) AS product_count FROM Category c
                LEFT JOIN product_category pc ON c.category_uuid = pc.category_id
                WHERE c.category_uuid = ?
                GROUP BY c.category_uuid, c.name;
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, categoryId);

        log.info("count of product in category " + categoryId + " is " + count);
        return categoryId;
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


}
