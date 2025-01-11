package com.saha.amit.repository;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.mapper.CustomerProfileOrderCountResultSetExtractor;
import com.saha.amit.mapper.CustomerProfileOrderResultSetExtractor;
import com.saha.amit.mapper.ProfileRowMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerRepositoryJdbc {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    Log log = LogFactory.getLog(CustomerRepositoryJdbc.class);
    public CustomerRepositoryJdbc(JdbcTemplate jdbcTemplate) {
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


    public ProfileDto findCustomerProfileById(Long profileUuid) {
        String sql = "SELECT * FROM Profile WHERE profile_uuid = ?";
        return jdbcTemplate.queryForObject(sql, new ProfileRowMapper(), profileUuid);
    }

    public List<ProfileDto> findCustomerWithNameAndEmail(String name, String email) {
        String sql = "SELECT * FROM Profile WHERE UPPER(name) LIKE UPPER(?) AND UPPER(email) LIKE UPPER(?)";
        String namePattern = "%" + name + "%";
        String emailPattern = "%" + email + "%";
        return jdbcTemplate.query(sql, new ProfileRowMapper(), namePattern, emailPattern);
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

        return jdbcTemplate.query(sql, new CustomerProfileOrderResultSetExtractor(), customerUuid);
    }


    public List<CustomerProfileOrderDto> findCustomersWithProfilesAndOrdersByEmail(String email) {
        String sql = "SELECT c.customer_uuid, c.customer_name, " +
                "p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, " +
                "o.order_uuid, o.order_number " +
                "FROM Customer c " +
                "JOIN Profile p ON c.customer_uuid = p.profile_uuid " +
                "LEFT JOIN Orders o ON c.customer_uuid = o.customer_id " +
                "WHERE UPPER(p.email) LIKE UPPER(?)";
        return jdbcTemplate.query(sql, new CustomerProfileOrderResultSetExtractor(), "%" + email + "%");
    }


    public List<CustomerProfileOrderDto> findCustomersWithProfilesAndOrdersNameAndCount(String name, int count) {
        String sql = """
                WITH RankedOrders AS (
                    SELECT c.customer_uuid AS customer_uuid, c.customer_name AS customer_name, p.profile_uuid AS profile_uuid, p.email AS email, p.name AS profile_name,
                     p.phone_number AS phone_number, p.street AS street, p.city AS city, p.state AS state, p.zip_code AS zip_code, COUNT(o.order_uuid)
                     OVER (PARTITION BY c.customer_uuid) AS order_count, o.order_uuid AS order_uuid, o.order_number AS order_number
                    FROM Customer c
                    JOIN Profile p ON c.customer_uuid = p.profile_uuid
                    LEFT JOIN Orders o ON c.customer_uuid = o.customer_id
                    WHERE UPPER(c.customer_name) LIKE UPPER(?)
                )
                SELECT customer_uuid, customer_name, profile_uuid, email, profile_name, phone_number, street, city, state, zip_code, order_count, order_uuid, order_number
                FROM RankedOrders WHERE order_count >= ?""";
        String namePattern = "%" + name + "%";
        return jdbcTemplate.query(sql, new CustomerProfileOrderCountResultSetExtractor(), namePattern, count);
    }

}
