package com.saha.amit;

import com.saha.amit.jdbc.dao.PersonJbdcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBasicMainApplication implements CommandLineRunner {
    @Autowired
    PersonJbdcDao personJbdcDao;

    private Logger log = LoggerFactory.getLogger(SpringBootBasicMainApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBasicMainApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("All users -> {}", personJbdcDao.findAll1());
    }
}