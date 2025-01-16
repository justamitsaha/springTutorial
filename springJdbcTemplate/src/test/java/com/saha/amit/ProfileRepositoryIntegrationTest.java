package com.saha.amit;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringJUnitConfig
@JdbcTest
@Sql(scripts = "classpath:test-schema.sql")
public class ProfileRepositoryIntegrationTest {

    @Autowired
    private CustomerRepositoryJdbc customerRepositoryJdbc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Log log = LogFactory.getLog(ProfileRepositoryIntegrationTest.class);


    @BeforeEach
    void setUp() {
        CustomerDto customerDto = new CustomerDto("john.doe@example.com", "John Doe", "555-1234", "123 Main St",
                "Any town", "Any state", "12345");
        CustomerDto customerDto1 = new CustomerDto("jane.doe@example.com", "Jane Doe", "555-5678", "456 Elm St",
                "Other town", "Other state", "67890");
        Long id = customerRepositoryJdbc.insertCustomer(customerDto);
        Long id2 = customerRepositoryJdbc.insertCustomer(customerDto1);
        log.info("Inserted users"+ id + id2);

        String orderSql = "INSERT INTO Orders (order_uuid, order_number, customer_id) VALUES ('order1', '10001', 1),\n" +
                "('order2', '10002', 1),\n" +
                "('order3', '10003', 2);";
        log.info("No or orders inserted "+jdbcTemplate.update(orderSql));
    }

    @Test
    public void testFindById() {
        CustomerDto customerDto = new CustomerDto("john.doe@example.com", "John Doe", "555-1234", "123 Main St",
                "Any town", "Any state", "12345");
        ProfileDto fetchCustomer = customerRepositoryJdbc.findCustomerProfileById(1L);
        assertEquals(fetchCustomer.getName(), customerDto.getName());
        assertEquals(fetchCustomer.getEmail(), customerDto.getEmail());
        assertEquals(fetchCustomer.getPhoneNumber(), customerDto.getPhoneNumber());
    }

    @Test
    void testFindAllCustomersWithProfilesAndOrders() {
        List<CustomerProfileOrderDto> customers = customerRepositoryJdbc.findAllCustomersWithProfilesAndOrders();
        assertNotNull(customers);
        assertEquals(2, customers.size());

        CustomerProfileOrderDto customer = customers.get(0);
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals("john.doe@example.com", customer.getProfile().getEmail());
        assertEquals(2, customer.getOrders().size());
    }

    @Test
    void testFindCustomersWithProfilesAndOrdersByEmail() {
        String emailFilter = "doe";
        List<CustomerProfileOrderDto> customers = customerRepositoryJdbc.findCustomersWithProfilesAndOrdersByEmail(emailFilter);
        assertNotNull(customers);
        assertEquals(2, customers.size());

        CustomerProfileOrderDto customer = customers.get(0);
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals("john.doe@example.com", customer.getProfile().getEmail());
        assertEquals(2, customer.getOrders().size());
    }
}
