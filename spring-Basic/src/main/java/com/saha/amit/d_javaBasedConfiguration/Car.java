package com.saha.amit.d_javaBasedConfiguration;

public class Car implements Vehicle {


    static int carInstanceCount = 0;

    public Car() {
        carInstanceCount++;
        System.out.println("Car instance count " + carInstanceCount);
    }

    @Override
    public void move() {
        System.out.println("Travel in car");
    }
}


