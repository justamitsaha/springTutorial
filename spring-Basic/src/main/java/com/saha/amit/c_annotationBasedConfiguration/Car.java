package com.saha.amit.c_annotationBasedConfiguration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("car")
@Primary
public class Car implements Vehicle {
    static int instanceCount = 0;

    public Car() {
        instanceCount++;
        System.out.println("Car instance count " + instanceCount);
    }
    @Override
    public void move() {
        System.out.println("Travel in car");
    }
}


