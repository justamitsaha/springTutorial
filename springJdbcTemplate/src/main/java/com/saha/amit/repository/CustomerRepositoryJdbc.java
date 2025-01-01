package com.saha.amit.repository;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.mapper.ProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class CustomerRepositoryJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int insertCustomer(CustomerDto customerDto) {
        // Insert into Profile table
        String profileSql = "INSERT INTO Profile (email, name, phone_number, street, city, state, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(profileSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, customerDto.getEmail());
                ps.setString(2, customerDto.getName());
                ps.setString(3, customerDto.getPhoneNumber());
                ps.setString(4, customerDto.getStreet());
                ps.setString(5, customerDto.getCity());
                ps.setString(6, customerDto.getState());
                ps.setString(7, customerDto.getZipCode());
                return ps;
            }
        }, keyHolder);

        // Retrieve the generated profile_uuid
        Long profileUuid = keyHolder.getKey().longValue();

        // Insert into Customer table using the generated profile_uuid
        String customerSql = "INSERT INTO Customer (customer_uuid, customer_name) VALUES (?, ?)";
        return jdbcTemplate.update(customerSql, profileUuid, customerDto.getName());
    }


    public ProfileDto findById(Long profileUuid) {
        String sql = "SELECT * FROM Profile WHERE profile_uuid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{profileUuid}, new ProfileRowMapper());
    }
}
