package com.saha.amit.g_beanLifeCycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanAnnotationDemo {

    public static void main(String[] args) {
        try(var applicationContext = new AnnotationConfigApplicationContext(AppConfig.class)){

            //List all the bean names
//            String[] beanNames = applicationContext.getBeanDefinitionNames();
//            for (String bean: beanNames){
//                System.out.println("Beans " +bean);
//            }

            Student student = (Student) applicationContext.getBean(Student.class);
            student.print();
        }
    }
}
