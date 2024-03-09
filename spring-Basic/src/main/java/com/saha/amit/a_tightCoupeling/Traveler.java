package com.saha.amit.a_tightCoupeling;

public class Traveler {
    Car car;

    public Traveler(Car car) {
        this.car = car;
    }

    public void startJourney() {
        this.car.move();
    }
}

