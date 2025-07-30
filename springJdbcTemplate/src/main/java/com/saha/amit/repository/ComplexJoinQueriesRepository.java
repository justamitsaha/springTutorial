package com.saha.amit.repository;

import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.mapper.CustomerProfileOrderCountResultSetExtractor;
import com.saha.amit.mapper.CustomerProfileOrderResultSetExtractor;
import com.saha.amit.mapper.ProfileRowMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComplexJoinQueriesRepository {

    private final JdbcTemplate jdbcTemplate;

    Log log = LogFactory.getLog(ComplexJoinQueriesRepository.class);

    @Autowired
    public ComplexJoinQueriesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProfileDto> findCustomerWithNameAndEmail(String name, String email) {
        String sql = "SELECT * FROM Profile WHERE UPPER(name) LIKE UPPER(?) AND UPPER(email) LIKE UPPER(?)";
        String namePattern = "%" + name + "%";
        String emailPattern = "%" + email + "%";
        return jdbcTemplate.query(sql, new ProfileRowMapper(), namePattern, emailPattern);
    }


    public List<CustomerProfileOrderDto> findAllCustomersWithProfilesAndOrders() {
        String sql = """
                SELECT c.customer_uuid, c.customer_name,
                p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, 
                o.order_uuid, o.order_number 
                FROM Customer c 
                JOIN Profile p ON c.customer_uuid = p.profile_uuid 
                LEFT JOIN Orders o ON c.customer_uuid = o.customer_id
                """;
        return jdbcTemplate.query(sql, new CustomerProfileOrderResultSetExtractor());
    }


    public List<CustomerProfileOrderDto> findCustomersWithProfilesAndOrdersByEmail(String email) {
        String sql = """
                SELECT c.customer_uuid, c.customer_name, \
                p.profile_uuid, p.email, p.name, p.phone_number, p.street, p.city, p.state, p.zip_code, \
                o.order_uuid, o.order_number \
                FROM Customer c \
                JOIN Profile p ON c.customer_uuid = p.profile_uuid \
                LEFT JOIN Orders o ON c.customer_uuid = o.customer_id \
                WHERE UPPER(p.email) LIKE UPPER(?)""";
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
