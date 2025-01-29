package com.saha.amit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBasicMainApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(SpringBootBasicMainApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBasicMainApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Start up work http://localhost:8080/swagger-ui/index.html#/");
    }
}