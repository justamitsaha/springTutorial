package com.saha.amit.c_annotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {
    @Autowired
    private ApplicationContext context;

    public Vehicle getVehicle(String type) {
        return context.getBean(type, Vehicle.class);
    }
}
