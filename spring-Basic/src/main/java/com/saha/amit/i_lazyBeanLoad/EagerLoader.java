package com.saha.amit.i_lazyBeanLoad;


import org.springframework.stereotype.Component;

@Component
public class EagerLoader {
    public EagerLoader() {
        System.out.println("EagerLoader..");
    }
}
