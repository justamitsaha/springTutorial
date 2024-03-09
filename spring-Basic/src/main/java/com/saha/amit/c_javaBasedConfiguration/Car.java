package com.saha.amit.c_javaBasedConfiguration;

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


