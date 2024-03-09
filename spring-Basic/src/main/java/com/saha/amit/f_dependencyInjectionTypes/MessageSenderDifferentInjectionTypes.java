package com.saha.amit.f_dependencyInjectionTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderDifferentInjectionTypes {


    //Field based DI
    @Autowired
    @Qualifier("emailService")
    private MessageService messageService;

    /*
    Constructor based DI
    Optional when we have only one constructor since Spring 4.3
     */
    @Autowired
    public MessageSenderDifferentInjectionTypes(@Qualifier("emailService") MessageService messageService){
        this.messageService= messageService;
    }

    //Setter based DI
    @Autowired
    public void setMessageService(@Qualifier("emailService") MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendMessage(String message){
        this.messageService.sendMessage(message);
    }
}
