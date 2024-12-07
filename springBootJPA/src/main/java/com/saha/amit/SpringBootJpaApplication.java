package com.saha.amit;


import com.github.javafaker.Faker;
import com.saha.amit.dto.AddressDto;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.model.*;
import com.saha.amit.repository.CategoryRepository;
import com.saha.amit.repository.ProductRepository;
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

    private final Log log = LogFactory.getLog(SpringBootJpaApplication.class);
    @Autowired
    CustomerService customerService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Swagger URL http://localhost:8080/swagger-ui/index.html#/");
        log.info("H2 console URL http://localhost:8080/h2-console/login.do");
        setUpData();
    }

    public void setUpData(){
        Faker faker = new Faker();
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setName(faker.commerce().department());
            categories.add(category);
        }
        categories = categoryRepository.saveAll(categories);

        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setPrice(faker.random().nextDouble());
            var count = faker.random().nextInt(0, 5);
            List<Category> categoryList = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                categoryList.add(categories.get(j));
            }
            product.setCategories(categoryList);
            productList.add(product);
            productRepository.save(product);
        }


        for (int i = 0; i < 10; i++) {
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

            var count1 = faker.random().nextInt(1, 5);
            List<Orders> ordersList = new ArrayList<>();
            for (int j = 0; j < count1; j++) {
                Orders order = new Orders();
                Payment payment = new Payment();
                payment.setPaymentStatus(PaymentStatus.SUCCESS);
                order.setOrderNumber(String.valueOf(faker.random().nextInt(10000, 99999)));
                order.setCustomer(customer);
                List<Product> products = new ArrayList<>();
                var count2 = faker.random().nextInt(1, 5);
                for (int k = 0; k < count2; k++) {
                    products.add(productList.get(faker.random().nextInt(0, 9)));
                }
                order.setProducts(products);
                ordersList.add(order);
            }
            customer.setOrders(ordersList);
            customerService.save(customer);
        }
    }
}