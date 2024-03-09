package com.saha.amit.e_stereoTypeAnnotations;

import com.saha.amit.e_stereoTypeAnnotations.controller.DemoController;
import com.saha.amit.e_stereoTypeAnnotations.repository.DemoRepository;
import com.saha.amit.e_stereoTypeAnnotations.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoClient {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        DemoController demoController = applicationContext.getBean(DemoController.class);
        System.out.println(demoController.hello());

        DemoService demoService = applicationContext.getBean(DemoService.class);
        System.out.println(demoService.hello());

        DemoRepository demoRepository = applicationContext.getBean(DemoRepository.class);
        System.out.println(demoRepository.hello());
    }
}
