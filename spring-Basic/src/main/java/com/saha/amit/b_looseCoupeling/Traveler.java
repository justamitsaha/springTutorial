package com.saha.amit.b_looseCoupeling;

public class Traveler {


    Vehicle vehicle;


    public Traveler(Vehicle vehicle){
        this.vehicle = vehicle;
    }


    public void startJourney(){
        this.vehicle.move();
    }


}

