package com.saha.amit.c_annotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {
    @Autowired
    private ApplicationContext context;

    /**
     * @param type name of the bean
     * @return Vehicle type Car/Bike based on the input
     * Based on the input param car/bike respective Class will be returned
     */
    public Vehicle getVehicle(String type) {
        return context.getBean(type, Vehicle.class);
    }
}
