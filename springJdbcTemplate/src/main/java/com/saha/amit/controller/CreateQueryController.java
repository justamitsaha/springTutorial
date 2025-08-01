package com.saha.amit.controller;

import com.saha.amit.AppConstants;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.repository.CreateQueryRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("create")
public class CreateQueryController {

    private final CreateQueryRepository query;

    @Autowired
    public CreateQueryController(CreateQueryRepository query) {
        this.query = query;
    }


    @Operation(summary = "Does LIKE operation on customer name and email ")
    @PutMapping("1/customer")
    public ResponseEntity<Long> insertCustomer(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok().body(query.insertCustomer(customerDto));
    }

    @Operation(summary = "Does LIKE operation on customer name and email ")
    @PutMapping("2/product/{productName}/{price}")
    public ResponseEntity<Long> addProductWithCategories(@PathVariable String productName,
                                                         @PathVariable double price,
                                                         @RequestBody List<Long> categoryIds){
        return ResponseEntity.ok().body(query.addProductWithCategories(productName, price, categoryIds));
    }

    @Operation(summary = AppConstants.CREATE_ORDER_DESCRIPTION)
    @PutMapping("3/order/{customerId}")
    public ResponseEntity<Integer> createOrders(@PathVariable long customerId, @RequestBody OrderDto orderDto){
        return ResponseEntity.ok().body(query.createOrders(orderDto,customerId));
    }

}
