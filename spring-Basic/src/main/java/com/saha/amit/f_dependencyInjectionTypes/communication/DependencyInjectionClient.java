package com.saha.amit.f_dependencyInjectionTypes.communication;

import com.saha.amit.f_dependencyInjectionTypes.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependencyInjectionClient {

    public static void main(String[] args) {

        String message = "Hi, good morning have a nice day!.";
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        try {
            ContactCustomer contactCustomer = applicationContext.getBean(ContactCustomer.class);
            contactCustomer.messageService.sendMessage(message);
            contactCustomer.messageService1.sendMessage(message);
            contactCustomer.getMessageService2().sendMessage(message);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
