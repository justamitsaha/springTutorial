package com.saha.amit;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.saha.amit")
public class SpringJdbcTemplateApplication implements CommandLineRunner {

    private final Log log = LogFactory.getLog(SpringJdbcTemplateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcTemplateApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Swagger URL http://localhost:8080/swagger-ui/index.html#/");
        log.info("H2 console URL http://localhost:8080/h2-console/login.do");
    }


}