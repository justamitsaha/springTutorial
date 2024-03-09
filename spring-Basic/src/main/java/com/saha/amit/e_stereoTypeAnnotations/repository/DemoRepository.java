package com.saha.amit.e_stereoTypeAnnotations.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {

    public String hello(){
        return "Hello repository";
    }
}
