package com.saha.amit.c_annotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBasedSpringClient {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Traveler traveler = applicationContext.getBean(Traveler.class);
        traveler.startJourney();

        // Creating bean dynamically using factory method
        Traveler traveler3 = applicationContext.getBean(Traveler.class);
        traveler3.setVehicle("car");
        traveler3.startJourney();

//        Car car1 = applicationContext.getBean(Car.class);
//        Car car2 = applicationContext.getBean(Car.class);  // will not trigger constructor again
    }
}
