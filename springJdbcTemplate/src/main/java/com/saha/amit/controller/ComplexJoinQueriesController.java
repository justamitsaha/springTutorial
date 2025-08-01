package com.saha.amit.controller;

import com.saha.amit.dto.CustomerProfileOrderDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.ComplexJoinQueriesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("complexJoin")
public class ComplexJoinQueriesController {

    private final ComplexJoinQueriesRepository query;

    @Autowired
    public ComplexJoinQueriesController(ComplexJoinQueriesRepository query) {
        this.query = query;
    }

    @Operation(summary = "Does LIKE operation on customer name and email ")
    @GetMapping("1/getCustomerNameEmailLike/{name}/{email}")
    public ResponseEntity<List<ProfileDto>> findCustomerWithNameAndEmail(@Parameter(description = "Name of the customer", example = "customer") @PathVariable String name, @Parameter(description = "Email of the customer", example = "Customer") @PathVariable String email) {
        return ResponseEntity.ok().body(query.findCustomerWithNameAndEmail(name, email));
    }

    @Operation(summary = "CUSTOMER/PROFILE INNER JOIN & CUSTOMER/ORDER LEFT JOIN")
    @GetMapping("2/orders")
    public ResponseEntity<List<CustomerProfileOrderDto>> findAllCustomersWithProfilesAndOrders() {
        return ResponseEntity.ok().body(query.findAllCustomersWithProfilesAndOrders());
    }

    @Operation(summary = "CUSTOMER/PROFILE INNER JOIN & CUSTOMER/ORDER LEFT JOIN and PROFILE.EMAIL LIKE")
    @GetMapping("3/customer/{email}")
    public ResponseEntity<List<CustomerProfileOrderDto>> findCustomersWithProfilesAndOrdersByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(query.findCustomersWithProfilesAndOrdersByEmail(email));
    }


    @Operation(summary = "CUSTOMER + PROFILE + LEFT JOIN ORDERS WHERE CUSTOMER_ID = AND ORDER_COUNT GREATER THAN")
    @GetMapping("7/customer/{count}/{name}")
    public ResponseEntity<List<CustomerProfileOrderDto>> findCustomersWithProfilesAndOrdersNameAndCount(
            @Parameter(description = "Name of the customer", example = "customer") @PathVariable String name,
            @Parameter(description = "No of orders of customers", example = "2") @PathVariable int count) {
        return ResponseEntity.ok().body(query.findCustomersWithProfilesAndOrdersNameAndCount(name, count));
    }
}
