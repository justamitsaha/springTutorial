package com.saha.amit.controller;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("2/{profileUuid}")
    public ResponseEntity<ProfileDto> findCustomerProfileById(@PathVariable Long profileUuid) {
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomerProfileById(profileUuid));
    }

    @GetMapping("3/customers/orders")
    public ResponseEntity<List<CustomerProfileOrderDto>> findAllCustomersWithProfilesAndOrders(){
        return ResponseEntity.ok().body(customerRepositoryJdbc.findAllCustomersWithProfilesAndOrders());
    }

    @GetMapping("4/customer/{email}")
    public ResponseEntity<List<CustomerProfileOrderDto>> findCustomersWithProfilesAndOrdersByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomersWithProfilesAndOrdersByEmail(email));
    }

}
