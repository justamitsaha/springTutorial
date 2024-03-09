package com.saha.amit.a_tightCoupeling;

public class Client {
    public static void main(String[] args) {
        Car car = new Car();
        Traveler traveler = new Traveler(car);
        traveler.startJourney();
    }
}

