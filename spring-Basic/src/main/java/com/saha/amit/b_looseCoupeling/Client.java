package com.saha.amit.b_looseCoupeling;

public class Client {
    public static void main(String[] args) {
        Vehicle car = new Car();
        Traveler traveler = new Traveler(car);
        traveler.startJourney();
    }
}

