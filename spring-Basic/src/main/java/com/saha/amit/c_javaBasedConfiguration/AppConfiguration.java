package com.saha.amit.c_javaBasedConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Vehicle car(){
        return  new Car();
    }
    @Bean
    public Vehicle bike(){
        return  new Bike();
    }

    @Bean(name = "travelerWithCar")
    public Traveler travelerWithCar(){
        return  new Traveler(car());
    }

    @Bean(name = "travelerWithBike")
    public Traveler travelerWithBike(){
        return  new Traveler(bike());
    }
}

