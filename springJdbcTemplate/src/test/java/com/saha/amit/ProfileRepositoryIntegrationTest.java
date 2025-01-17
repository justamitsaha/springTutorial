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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;





@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringJUnitConfig
//@JdbcTest
//@Sql(scripts = "classpath:schema/test-schema.sql")
public class ProfileRepositoryIntegrationTest {

    @Autowired
    private CustomerRepositoryJdbc customerRepositoryJdbc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Long id;
    Long id2;

    Log log = LogFactory.getLog(ProfileRepositoryIntegrationTest.class);


    @BeforeEach
    void setUp() {
        String profile = "DELETE FROM Profile WHERE EMAIL LIKE '%doe%'";
        String customer = "DELETE FROM Customer WHERE customer_name LIKE '%Doe 1%'";
        String order = "DELETE FROM ORDERS WHERE order_number IN ('10014', '10015', '10016') ";

        log.info(jdbcTemplate.update(profile));
        log.info(jdbcTemplate.update(customer));
        log.info(jdbcTemplate.update(order));

        CustomerDto customerDto = new CustomerDto("john.doe1@example.com", "John Doe 1", "555-1234", "123 Main St",
                "Any town", "Any state", "12345");
        CustomerDto customerDto1 = new CustomerDto("jane.doe1@example.com", "Jane Doe 1", "555-5678", "456 Elm St",
                "Other town", "Other state", "67890");
        id = customerRepositoryJdbc.insertCustomer(customerDto);
        id2 = customerRepositoryJdbc.insertCustomer(customerDto1);
        log.info("Inserted users"+ id + id2);

        String orderSql = "INSERT INTO Orders (order_uuid, order_number, customer_id) VALUES " +
                "('order14', '10014', "+id+"),\n" +
                "('order15', '10015', "+id+"),\n" +
                "('order16', '10016', "+id2+");";
        log.info("No or orders inserted "+jdbcTemplate.update(orderSql));
    }

    @Test
    public void testFindById() {
        CustomerDto customerDto = new CustomerDto("john.doe1@example.com", "John Doe 1", "555-1234", "123 Main St",
                "Any town", "Any state", "12345");
        ProfileDto fetchCustomer = customerRepositoryJdbc.findCustomerProfileById(id);
        assertEquals(fetchCustomer.getName(), customerDto.getName());
        assertEquals(fetchCustomer.getEmail(), customerDto.getEmail());
        assertEquals(fetchCustomer.getPhoneNumber(), customerDto.getPhoneNumber());
    }

    @Test
    void testFindAllCustomersWithProfilesAndOrders() {
        List<CustomerProfileOrderDto> customers = customerRepositoryJdbc.findAllCustomersWithProfilesAndOrders();
        assertNotNull(customers);
        assertEquals(14, customers.size());

        CustomerProfileOrderDto customer = customers.get(Math.toIntExact(id-1));
        assertEquals("John Doe 1", customer.getCustomerName());
        assertEquals("john.doe1@example.com", customer.getProfile().getEmail());
        assertEquals(2, customer.getOrders().size());
    }

    @Test
    void testFindCustomersWithProfilesAndOrdersByEmail() {
        String emailFilter = "doe1";
        List<CustomerProfileOrderDto> customers = customerRepositoryJdbc.findCustomersWithProfilesAndOrdersByEmail(emailFilter);
        assertNotNull(customers);
        assertEquals(2, customers.size());

        CustomerProfileOrderDto customer = customers.get(0);
        assertEquals("John Doe 1", customer.getCustomerName());
        assertEquals("john.doe1@example.com", customer.getProfile().getEmail());
        assertEquals(2, customer.getOrders().size());
    }
}
