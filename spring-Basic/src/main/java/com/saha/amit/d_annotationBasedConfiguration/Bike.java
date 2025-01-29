package com.saha.amit.d_annotationBasedConfiguration;

import org.springframework.stereotype.Component;

@Component("bike")
public class Bike implements Vehicle {
    @Override
    public void move(){
        System.out.println("Travel in bike");
    }
}

