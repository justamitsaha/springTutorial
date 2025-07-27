package com.saha.amit.controller;


import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.CustomerRepositoryJdbc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerRepositoryJdbc customerRepositoryJdbc;

    @Operation(summary = "Save customer")
    @PostMapping("1/save")
    public ResponseEntity<Long> insertUser(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerRepositoryJdbc.insertCustomer(customerDto));
    }

    @Operation(summary = "FETCH PROFILE")
    @GetMapping("2/{profileUuid}")
    public ResponseEntity<ProfileDto> findCustomerProfileById(@PathVariable Long profileUuid) {
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomerProfileById(profileUuid));
    }

    @Operation(summary = "FIND CUSTOMER WHERE EMAIL AND NAME LIKE")
    @GetMapping("3/{name}/{email}")
    public ResponseEntity<List<ProfileDto>> findCustomerWithNameAndEmail(
            @Parameter(description = "Name of the customer", example = "customer") @PathVariable String name,
            @Parameter(description = "Email of the customer", example = "Customer") @PathVariable String email) {
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomerWithNameAndEmail(name, email));
    }

    @Operation(summary = "CUSTOMER + PROFILE + LEFT JOIN ORDERS")
    @GetMapping("4/orders")
    public ResponseEntity<List<CustomerProfileOrderDto>> findAllCustomersWithProfilesAndOrders() {
        return ResponseEntity.ok().body(customerRepositoryJdbc.findAllCustomersWithProfilesAndOrders());
    }

    @Operation(summary = "CUSTOMER + PROFILE + LEFT JOIN ORDERS WHERE CUSTOMER_ID = ")
    @GetMapping("5/orders/{profileUuid}")
    public ResponseEntity<List<CustomerProfileOrderDto>> findCustomersWithProfilesAndOrdersByCustomerUuid(Long customerUuid) {
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomersWithProfilesAndOrdersByCustomerUuid(customerUuid));
    }

    @Operation(summary = "CUSTOMER + PROFILE + LEFT JOIN ORDERS WHERE CUSTOMER_ID = AND EMAIL LIKE")
    @GetMapping("6/{email}")
    public ResponseEntity<List<CustomerProfileOrderDto>> findCustomersWithProfilesAndOrdersByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomersWithProfilesAndOrdersByEmail(email));
    }

    @Operation(summary = "CUSTOMER + PROFILE + LEFT JOIN ORDERS WHERE CUSTOMER_ID = AND COUNT GREATER THAN")
    @GetMapping("7/{count}/{name}")
    public ResponseEntity<List<CustomerProfileOrderDto>> findCustomersWithProfilesAndOrdersNameAndCount(
            @Parameter(description = "Name of the customer", example = "customer") @PathVariable String name,
            @Parameter(description = "No of orders of customers", example = "2") @PathVariable int count) {
        return ResponseEntity.ok().body(customerRepositoryJdbc.findCustomersWithProfilesAndOrdersNameAndCount(name, count));
    }

}
