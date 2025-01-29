package com.saha.amit.configManagement.controller;

import com.saha.amit.configManagement.configuration.UserProfile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class CurrencyController {

    @Value("${payment.salary}")
    private int salary;

    @Value("${payment.message}")
    private String message;

    @Autowired
    private UserProfile userProfile;

    private final Log log = LogFactory.getLog(CurrencyController.class);

    @Autowired
    private Environment environment;

    @RequestMapping("user")
    public UserProfile getCurrencyConfiguration() {
        return userProfile;
    }

    @GetMapping("rate")
    public String getRate() {
        return salary + message;
    }

    @GetMapping("env")
    public String getEnv() {
        log.info(environment.getProperty("JAVA_HOME") + environment.getProperty("myCustomProp"));
        return environment.getProperty("JAVA_HOME") + environment.getProperty("myCustomProp");
    }
}
