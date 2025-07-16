package com.saha.amit.c_annotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBasedSpringClient {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);


        Car car1 = applicationContext.getBean(Car.class);
        Car car2 = applicationContext.getBean(Car.class);  // will not trigger constructor again


        Traveler traveler = applicationContext.getBean(Traveler.class);
        traveler.startJourney();

        Traveler traveler2 = applicationContext.getBean(Traveler.class);
        traveler2.startJourney();

        Traveler traveler3 = applicationContext.getBean(Traveler.class);
        traveler3.setVehicle("car");        // Creating bean dynamically using factory method
        traveler3.startJourney();
    }
}
