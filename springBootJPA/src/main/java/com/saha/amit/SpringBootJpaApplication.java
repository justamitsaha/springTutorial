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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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

    @Transactional
    public void setUpData() {
        Faker faker = new Faker();
        List<Category> categories = new ArrayList<>();

        // Create and save categories
        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setName(faker.commerce().department());
            categories.add(category);
        }
        categories = categoryRepository.saveAll(categories);

        // Create and save products
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            Product product = new Product();
            product.setName(faker.commerce().productName() +i);
            product.setPrice(faker.number().randomDouble(2, 10, 1000));
            int count = faker.random().nextInt(1, 5);
            Set<Category> categoryList = new HashSet<>();
            for (int j = 0; j < count; j++) {
                categoryList.add(categories.get(faker.random().nextInt(0, categories.size() - 1)));
            }
            product.setCategories(categoryList);
            productList.add(product);
        }
        productList = productRepository.saveAll(productList);

        // Create and save customers with orders and payments
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
            String name = faker.funnyName().name();
            profile.setName(name);

            Customer customer = new Customer();
            customer.setCustomerName(name);
            customer.setProfile(profile);

            int count1 = faker.random().nextInt(1, 15);
            List<Orders> ordersList = new ArrayList<>();
            for (int j = 0; j < count1; j++) {
                Orders order = new Orders();
                order.setOrderNumber(String.valueOf(faker.number().numberBetween(10000, 99999)));
                List<Product> products = new ArrayList<>();
                int count2 = faker.random().nextInt(1, 5);
                for (int k = 0; k < count2; k++) {
                    products.add(productList.get(faker.random().nextInt(0, productList.size() - 1)));
                }
                order.setProducts(products);

                Payment payment = new Payment();
                Random random = new Random();
                var result = switch (random.nextInt(1,4)){
                    case 1 -> PaymentStatus.SUCCESS;
                    case 2 -> PaymentStatus.FAILURE;
                    default -> PaymentStatus.PROCESSING;
                };
                payment.setPaymentStatus(result);
                payment.setOrder(order);
                order.setPayment(payment);

                order.setCustomer(customer);  // Set the customer for each order
                ordersList.add(order);
            }
            customer.setOrders(ordersList);
            customerService.save(customer);
        }
    }

}