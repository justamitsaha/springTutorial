package com.saha.amit.c_annotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("traveler")
public class Traveler {

    Vehicle vehicle;

    //@Qualifier will overwrite @Primary
    @Autowired
    public Traveler(@Qualifier("bike") Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @Autowired
    private VehicleFactory factory;


    public void startJourney(){
        this.vehicle.move();
    }

    /**
     * @param type String will tell which type of bean to load Car/Bike
     *             This method will set the dependency dynamically by passing Car/Bike
     */
    public void setVehicle(String type) {
        this.vehicle = factory.getVehicle(type);
    }

}

