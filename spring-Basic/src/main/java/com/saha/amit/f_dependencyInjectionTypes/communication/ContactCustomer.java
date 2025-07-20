package com.saha.amit.f_dependencyInjectionTypes.communication;

import com.saha.amit.f_dependencyInjectionTypes.communication.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ContactCustomer {


    //Field based DI
    @Autowired
    @Qualifier("emailService")
    MessageService messageService;


    final MessageService messageService1;
    /*
    Constructor based DI
    Optional when we have only one constructor since Spring 4.3
     */
    @Autowired
    public ContactCustomer(@Qualifier("smsService") MessageService messageService1){
        this.messageService1 = messageService1;
    }


    private MessageService messageService2;

    public MessageService getMessageService2() {
        return messageService2;
    }

    @Autowired
    public void setMessageService2(@Qualifier("push")MessageService messageService2) {
        this.messageService2 = messageService2;
    }

}
