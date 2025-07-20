package com.saha.amit.g_beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Student implements InitializingBean, DisposableBean,
        BeanNameAware, BeanFactoryAware, ApplicationContextAware {


    public void print(){
        System.out.println("1. Student class method called ...");
    }



    @Override
    public void setBeanName(String name) {
        System.out.println("2. BeanNameAware: Bean name is " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("3. ApplicationContextAware: Context received");
    }


    @Override
    public void afterPropertiesSet() {
        System.out.println("5. InitializingBean: afterPropertiesSet called");
    }

    public void customInit() {
        System.out.println("6. customInit: defined in @Bean initMethod");
    }


    @Override
    public void destroy() {
        System.out.println("8. DisposableBean: destroy called");
    }

    public void customDestroy() {
        System.out.println("9. customDestroy: defined in @Bean destroyMethod");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("10. BeanFactoryAware: beanFactory is " + beanFactory);
    }
}
