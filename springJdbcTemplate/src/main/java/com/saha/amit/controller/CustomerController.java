package com.saha.amit.controller;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final Log log = LogFactory.getLog(CustomerController.class);

    @Autowired
    CustomerRepositoryJdbc customerRepositoryJdbc;

    @PostMapping("1/save")
    public ResponseEntity<Long> insertUser(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok().body(customerRepositoryJdbc.insertCustomer(customerDto));
    }


    public ResponseEntity<List<CustomerProfileOrderDto>> fetchUserById(){
        return ResponseEntity.ok().body(customerRepositoryJdbc.findAllCustomersWithProfilesAndOrders());
    }

}
