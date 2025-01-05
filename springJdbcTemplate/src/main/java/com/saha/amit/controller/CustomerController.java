package com.saha.amit.controller;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

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

    @GetMapping("3/{name}/{email}")
    public ResponseEntity<List<ProfileDto>> findCustomerWithNameAndEmail(@PathVariable String name,@PathVariable String email) {
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomerWithNameAndEmail(name,email));
    }

    @GetMapping("4/customers/orders")
    public ResponseEntity<List<CustomerProfileOrderDto>> findAllCustomersWithProfilesAndOrders(){
        return ResponseEntity.ok().body(customerRepositoryJdbc.findAllCustomersWithProfilesAndOrders());
    }

    @GetMapping("5/customer/{email}")
    public ResponseEntity<List<CustomerProfileOrderDto>> findCustomersWithProfilesAndOrdersByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomersWithProfilesAndOrdersByEmail(email));
    }

}
