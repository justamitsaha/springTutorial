package com.saha.amit.c_annotationBasedConfiguration;

import org.springframework.stereotype.Component;

@Component("bike")      //name for bean
public class Bike implements Vehicle {
    @Override
    public void move(){
        System.out.println("Travel in bike");
    }
}

