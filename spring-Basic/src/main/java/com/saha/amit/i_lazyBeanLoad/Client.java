package com.saha.amit.i_lazyBeanLoad;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        System.out.println("Starting application context...");
        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);    //Eager will be loaded now
        System.out.println("Application context started!");

        EagerLoader eagerLoader = applicationContext.getBean(EagerLoader.class);
        LazyLoader lazyLoader = applicationContext.getBean(LazyLoader.class);                       //Lazy will be loaded now

    }
}
