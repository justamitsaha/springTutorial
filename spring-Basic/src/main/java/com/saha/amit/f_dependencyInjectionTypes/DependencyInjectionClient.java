package com.saha.amit.f_dependencyInjectionTypes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependencyInjectionClient {

    public static void main(String[] args) {

        String message = "Hi, good morning have a nice day!.";
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MessageSenderDifferentInjectionTypes messageSender = applicationContext.getBean(MessageSenderDifferentInjectionTypes.class);
        messageSender.sendMessage(message);
    }
}
