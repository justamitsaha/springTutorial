package com.saha.amit.d_annotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBasedSpringClient {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);


        Car car1 = applicationContext.getBean(Car.class);
        Traveler traveler = applicationContext.getBean(Traveler.class);
        traveler.startJourney();


        Car car2 = applicationContext.getBean(Car.class);  // will not trigger constructor again
        Traveler traveler2 = applicationContext.getBean(Traveler.class);
        traveler2.startJourney();


        Vehicle car0 = new Car();  // Will trigger constructor again
        new Traveler(car0).startJourney();
    }
}
