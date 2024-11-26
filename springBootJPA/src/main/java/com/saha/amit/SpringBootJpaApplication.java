package com.saha.amit;


import com.github.javafaker.Faker;
import com.saha.amit.dto.AddressDto;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.model.*;
import com.saha.amit.repository.CategoryRepository;
import com.saha.amit.service.CustomerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryRepository categoryRepository;

    private final Log log = LogFactory.getLog(SpringBootJpaApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Swagger URL http://localhost:8080/swagger-ui/index.html#/");
        log.info("H2 console URL http://localhost:8080/h2-console/login.do");

        Faker faker = new Faker();

        for (int i=0; i<10; i++){
            Category category = new Category();
            category.setName(faker.commerce().department());
            categoryRepository.save(category);
        }

        for (int i=0; i<10; i++){
            Address address = new Address();
            address.setCity(faker.address().city());
            address.setState(faker.address().state());
            address.setStreet(faker.address().streetAddress());
            address.setZipCode(faker.address().zipCode());

            Profile profile = new Profile();
            profile.setEmail(faker.internet().emailAddress());
            profile.setPhoneNumber(faker.phoneNumber().cellPhone());
            profile.setAddress(address);

            Customer customer = new Customer();
            customer.setName(faker.funnyName().name());
            customer.setProfile(profile);
            customerService.save(customer);

            for (int j=0; j< faker.random().nextInt(1,5);j++){
                Orders orders = new Orders();

            }
        }

    }


}