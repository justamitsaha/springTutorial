package com.saha.amit.e_stereoTypeAnnotations.controller;


import com.saha.amit.e_stereoTypeAnnotations.service.DemoService;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {

    private DemoService demoService;

    public String hello(){
        return "hello controller";
    }
}
