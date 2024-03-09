package com.saha.amit.d_annotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("traveler")
public class Traveler {

    Vehicle vehicle;

//     In case if @Primary is not mentioned
//    @Autowired
//    public Traveler(@Qualifier("car") Vehicle vehicle){
//        this.vehicle = vehicle;
//    }


    @Autowired
    public Traveler(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void startJourney(){
        this.vehicle.move();
    }
}

