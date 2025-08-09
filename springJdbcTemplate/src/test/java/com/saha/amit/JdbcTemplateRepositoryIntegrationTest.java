package com.saha.amit;

import com.saha.amit.dto.ProductDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import com.saha.amit.repository.SimpleQueryRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
//@Sql(scripts = "classpath:test-schema.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JdbcTemplateRepositoryIntegrationTest {

    Log log = LogFactory.getLog(JdbcTemplateRepositoryIntegrationTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleQueryRepository simpleQueryRepository;


    @BeforeEach
    void setUp() {

    }

    @Test
    void testProductCount() {
        Integer count = simpleQueryRepository.getProductCount();
        assertEquals(10, count);
    }


    @Test
    void testProductInProductId() {
        List<Long> list = List.of(1L, 5L, 9L, 8L);
        Integer count = simpleQueryRepository.productInProductId(list);
        assertEquals(list.size(), count);
    }

    @Test
    void testFindCustomerProfileById() {
        ProfileDto profileDto = new ProfileDto("john.doe@yahoo.com", "John Doe", "555-1234",
                "123 Main St", "Any town", "Any state", "12345");

        ProfileDto profileDtoResult = simpleQueryRepository.findCustomerProfileById(1L);
        assertEquals(profileDto.getName(), profileDtoResult.getName());
        assertEquals(profileDto.getPhoneNumber(), profileDtoResult.getPhoneNumber());
    }

    @Test
    void testGetAllProducts(){
        List<ProductDto> productDtoList = simpleQueryRepository.getAllProducts();
        assertTrue(productDtoList.size()>0);
    }

    @Test
    void testProduct(){
        var product = simpleQueryRepository.getAllProducts();
        product.forEach(log::info);
    }


}
