package com.saha.amit;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringJUnitConfig
@JdbcTest
@Sql(scripts = "classpath:scripts/test-schema.sql")
public class ProfileRepositoryIntegrationTestStandAlone {

    @Autowired
    private CustomerRepositoryJdbc customerRepositoryJdbc;

    @Test
    public void testFindById() {
        CustomerDto customerDto = new CustomerDto("test@example.com", "Test User", "1234567890", "123 Street",
                "City", "State", "12345");
        Long id = customerRepositoryJdbc.insertCustomer(customerDto);
        ProfileDto fetchCustomer = customerRepositoryJdbc.findCustomerProfileById(id);
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
