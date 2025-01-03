package com.saha.amit;

import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
Integration tests verify the behavior of a group of components working together.
They test the interactions between different parts of the application, including the application’s infrastructure (database, web server, etc.).
Load the full application context using Spring's dependency injection framework.
Use annotations like @SpringBootTest to start the application context.
 */
@JdbcTest
public class ProfileRepositoryIntegrationTest {

    @Autowired
    private CustomerRepositoryJdbc customerRepositoryJdbc;


    @BeforeEach
    @Sql("test-schema.sql")        // This should create the schema and insert test data
    void setUp() {
    }

    @Test
    void testInsertCustomer() {
//        CustomerDto customerDto = new CustomerDto("test@example.com", "Test User", "1234567890", "123 Street",
//                "City", "State", "12345");
//        Long id = customerRepositoryJdbc.insertCustomer(customerDto);
        ProfileDto fetchCustomer = customerRepositoryJdbc.findCustomerProfileById(13L);
        assertNotNull(fetchCustomer);
//        assertEquals(fetchCustomer.getName(), customerDto.getName());
//        assertEquals(fetchCustomer.getEmail(), customerDto.getEmail());
//        assertEquals(fetchCustomer.getPhoneNumber(), customerDto.getPhoneNumber());
    }
}
