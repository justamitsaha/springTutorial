package com.saha.amit.h_beanScope;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanScopeClient {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);     //Will call constructor

        SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);
        SingletonBean singletonBean1 = applicationContext.getBean(SingletonBean.class);     //Will not call constructor

    }

}
