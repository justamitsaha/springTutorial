package com.saha.amit.repository;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.mapper.CustomerProfileOrderResultSetExtractor;
import com.saha.amit.mapper.CustomerProfileOrderRowMapper;
import com.saha.amit.mapper.ProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerRepositoryJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Long insertCustomer(CustomerDto customerDto) {
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
        Long profileUuid = Objects.requireNonNull(keyHolder.getKey()).longValue();

        // Insert into Customer table using the generated profile_uuid
        String customerSql = "INSERT INTO Customer (customer_uuid, customer_name) VALUES (?, ?)";
        if(jdbcTemplate.update(customerSql, profileUuid, customerDto.getName()) > 0)
            return profileUuid;
        else
            return  -1L;
    }


    public ProfileDto findCustomerProfileById(Long profileUuid) {
        String sql = "SELECT * FROM Profile WHERE profile_uuid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{profileUuid}, new ProfileRowMapper());
    }

    public List<CustomerProfileOrderDto> findAllCustomersWithProfilesAndOrders() {
        String sql = "SELECT c.customer_uuid, c.customer_name, " +
                "p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, " +
                "o.order_uuid, o.order_number " +
                "FROM Customer c " +
                "JOIN Profile p ON c.customer_uuid = p.profile_uuid " +
                "LEFT JOIN Orders o ON c.customer_uuid = o.customer_id";

        return jdbcTemplate.query(sql, new CustomerProfileOrderResultSetExtractor());
    }

    public List<CustomerProfileOrderDto> findCustomersWithProfilesAndOrdersByCustomerUuid(Long customerUuid) {
        String sql = "SELECT c.customer_uuid, c.customer_name, " +
                "p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, " +
                "o.order_uuid, o.order_number " +
                "FROM Customer c " +
                "JOIN Profile p ON c.customer_uuid = p.profile_uuid " +
                "LEFT JOIN Orders o ON c.customer_uuid = o.customer_id " +
                "WHERE c.customer_uuid = ?";

        return jdbcTemplate.query(sql, new Object[]{customerUuid}, new CustomerProfileOrderResultSetExtractor());
    }


    public List<CustomerProfileOrderDto> findCustomersWithProfilesAndOrdersByEmail(String email) {
        String sql = "SELECT c.customer_uuid, c.customer_name, " +
                "p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, " +
                "o.order_uuid, o.order_number " +
                "FROM Customer c " +
                "JOIN Profile p ON c.customer_uuid = p.profile_uuid " +
                "LEFT JOIN Orders o ON c.customer_uuid = o.customer_id " +
                "WHERE p.email LIKE ?";

        return jdbcTemplate.query(sql, new Object[]{"%" + email + "%"}, new CustomerProfileOrderResultSetExtractor());
    }
}
