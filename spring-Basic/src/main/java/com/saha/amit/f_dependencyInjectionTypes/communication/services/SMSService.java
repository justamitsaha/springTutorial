package com.saha.amit.f_dependencyInjectionTypes.communication.services;

import org.springframework.stereotype.Component;

@Component("smsService")
public class SMSService implements MessageService{

    @Override
    public void sendMessage(String message){
        System.out.println("Sending SMS "+message);
    }
}
