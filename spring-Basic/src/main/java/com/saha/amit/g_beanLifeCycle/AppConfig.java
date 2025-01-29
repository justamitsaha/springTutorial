package com.saha.amit.g_beanLifeCycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig{

    @Bean(name = "addressBean")
    public Address address(){
        return new Address();
    }

    @Bean(name = {"studentBean","studentDemo"}, initMethod = "init", destroyMethod = "destroy")
    public Student student(){
        return new Student(address());
    }
}
