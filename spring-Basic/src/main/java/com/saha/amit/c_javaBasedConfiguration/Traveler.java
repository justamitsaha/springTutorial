package com.saha.amit.c_javaBasedConfiguration;

public class Traveler {


    Vehicle vehicle;


    public Traveler(Vehicle vehicle){
        this.vehicle = vehicle;
    }


    public void startJourney(){
        this.vehicle.move();
    }


}

