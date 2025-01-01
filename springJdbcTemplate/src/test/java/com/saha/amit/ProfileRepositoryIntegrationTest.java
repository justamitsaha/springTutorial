package com.saha.amit;

import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import com.saha.amit.repository.jpa.CategoryRepository;
import com.saha.amit.repository.jpa.CustomerRepository;
import com.saha.amit.repository.jpa.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
Integration tests verify the behavior of a group of components working together.
They test the interactions between different parts of the application, including the application’s infrastructure (database, web server, etc.).
Load the full application context using Spring's dependency injection framework.
Use annotations like @SpringBootTest to start the application context.
 */
@JdbcTest
@ComponentScan("com.saha.amit")  // Replace with your package name
public class ProfileRepositoryIntegrationTest {

    @Autowired
    private CustomerRepositoryJdbc customerRepositoryJdbc;

    @BeforeEach
    @Sql("scripts/test-schema.sql")
        // This should create the schema and insert test data
    void setUp() {
    }

    @Test
    void testFindById() {
        ProfileDto expectedProfile = new ProfileDto("test@example.com", "Test User", "1234567890",
                "123 Street", "City", "State", "12345");
        expectedProfile.setProfileUuid(11L);

        ProfileDto actualProfile = customerRepositoryJdbc.findById(11L);

        assertEquals(expectedProfile, actualProfile);
    }
}
