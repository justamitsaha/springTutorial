package com.saha.amit.f_dependencyInjectionTypes.communication.services;

import org.springframework.stereotype.Component;

@Component("push")
public class PushNotificationServices implements MessageService {

    @Override
    public void sendMessage(String message){
        System.out.println("Sending Push Notification "+message);
    }
}
