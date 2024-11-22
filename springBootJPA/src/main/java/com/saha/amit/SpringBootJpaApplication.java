package com.saha.amit;


import com.github.javafaker.Faker;
import com.saha.amit.dto.AddressDto;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.service.CustomerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

    @Autowired
    CustomerService customerService;

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
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(faker.address().city());
            addressDto.setState(faker.address().state());
            addressDto.setStreet(faker.address().streetAddress());
            addressDto.setZipCode(faker.address().zipCode());

            ProfileDto profileDto = new ProfileDto();
            profileDto.setEmail(faker.internet().emailAddress());
            profileDto.setPhoneNumber(faker.phoneNumber().cellPhone());

            CustomerDto customerDto = new CustomerDto();
            customerDto.setName(faker.funnyName().name());
            customerDto.setProfileDto(profileDto);
            customerDto.setAddress(addressDto);
            customerService.save(customerDto);
        }

    }


}