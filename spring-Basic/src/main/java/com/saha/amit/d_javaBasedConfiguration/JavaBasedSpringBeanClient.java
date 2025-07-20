package com.saha.amit.d_javaBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBasedSpringBeanClient  {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Traveler traveler = (Traveler) applicationContext.getBean("travelerWithCar");
        traveler.startJourney();
        Traveler traveler1 = (Traveler) applicationContext.getBean("travelerWithCar");
        traveler1.startJourney();

    }
}

