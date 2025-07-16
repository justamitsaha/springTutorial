package com.saha.amit.d_javaBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBasedSpringBeanClient  {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Car car1 = applicationContext.getBean(Car.class);
        Car car2 = applicationContext.getBean(Car.class); // will not trigger constructor again
        Bike bike1 = applicationContext.getBean(Bike.class);
        Bike bike2 = applicationContext.getBean(Bike.class); // will not trigger constructor again
        Vehicle car0 = new Car(); // Will trigger constructor again
        new Traveler(car0).startJourney();

        Traveler traveler = (Traveler) applicationContext.getBean("travelerWithCar");
        traveler.startJourney();
        Traveler traveler1 = (Traveler) applicationContext.getBean("travelerWithCar");
        traveler1.startJourney();
        Traveler traveler2 = (Traveler) applicationContext.getBean("travelerWithBike");
        traveler2.startJourney();

    }
}

