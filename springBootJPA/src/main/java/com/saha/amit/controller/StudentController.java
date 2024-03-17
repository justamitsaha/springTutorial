package com.saha.amit.controller;

import com.saha.amit.entity.StudentDetails;
import com.saha.amit.record.CreateStudent;
import com.saha.amit.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/createStudent")
    public ResponseEntity<StudentDetails> save(@RequestBody @Valid CreateStudent createUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createUser(createUser));
    }
}
