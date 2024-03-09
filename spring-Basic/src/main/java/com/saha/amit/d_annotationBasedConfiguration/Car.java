package com.saha.amit.d_annotationBasedConfiguration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("car")
@Primary
public class Car implements Vehicle {
    static int milesTravelled = 0;

    public Car() {
        milesTravelled++;
        System.out.println("Car mileage " + milesTravelled);
    }

    @Override
    public void move() {
        System.out.println("Travel in car");
    }
}


