package com.saha.amit.d_annotationBasedConfiguration;

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

    public void setVehicle(String type) {
        this.vehicle = factory.getVehicle(type);
    }

    public void startJourney(){
        this.vehicle.move();
    }
}

