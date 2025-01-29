package com.saha.amit.c_javaBasedConfiguration;

public class Bike implements Vehicle {

    static int bikeInstanceCount = 0;

    public Bike() {
        System.out.println("Bike instance count "+ bikeInstanceCount);
    }

    @Override
    public void move(){
        System.out.println("Travel in Bike");
    }
}

