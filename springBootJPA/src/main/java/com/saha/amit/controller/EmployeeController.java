package com.saha.amit.controller;


import com.saha.amit.entity.hasRelation.EmployeeHasAddress;
import com.saha.amit.repository.EmployeeHasAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeHasAddressRepository employeeHasAddressRepository;

    @PostMapping("/createStudent")
    public ResponseEntity<EmployeeHasAddress> save(@RequestBody EmployeeHasAddress employeeHasAddress) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeHasAddressRepository.save(employeeHasAddress));
    }
}
