package com.saha.amit.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int insertUser(String name) {
        String sql = "INSERT INTO CUSTOMER (customer_name) VALUES (?)";
        return jdbcTemplate.update(sql, name);
    }
}
