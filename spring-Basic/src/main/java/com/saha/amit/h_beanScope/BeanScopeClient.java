package com.saha.amit.h_beanScope;

import com.saha.amit.d_annotationBasedConfiguration.AppConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanScopeClient {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

    }

}
