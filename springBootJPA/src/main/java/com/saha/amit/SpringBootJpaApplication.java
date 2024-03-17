package com.saha.amit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJpaApplication {
    public static void main(String[] args) {
        System.out.println("Swagger URL http://localhost:8080/swagger-ui/index.html#/");
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }
}