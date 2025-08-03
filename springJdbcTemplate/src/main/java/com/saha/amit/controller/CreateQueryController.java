package com.saha.amit.controller;

import com.saha.amit.AppConstants;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProductDto;
import com.saha.amit.repository.CreateQueryRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("create")
public class CreateQueryController {

    Logger logger = LoggerFactory.getLogger(CreateQueryController.class);

    private final CreateQueryRepository query;

    @Autowired
    public CreateQueryController(CreateQueryRepository query) {
        this.query = query;
    }


    @Operation(summary = "Does LIKE operation on customer name and email ")
    @PutMapping("customer")
    public ResponseEntity<Long> insertCustomer(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok().body(query.insertCustomer(customerDto));
    }

    @Operation(summary = "Add product & categories", description = AppConstants.CREATE_PRODUCT_DESCRIPTION
    )
    @PutMapping("product")
    public ResponseEntity<Long> addProductWithCategories(@RequestBody ProductDto productDto){
        logger.info("Add product productDto: {}", productDto);
        return ResponseEntity.ok().body(query.addProductWithCategories(productDto.getName(),
                productDto.getPrice(),
                productDto.getCategoryIds()));
    }

    @Operation(summary = "Create order, Payments", description = AppConstants.CREATE_ORDER_DESCRIPTION)
    @PutMapping("order/{customerId}")
    public ResponseEntity<String> createOrders(@PathVariable long customerId, @RequestBody OrderDto orderDto){
        logger.info("createOrders order data: {} , customer id: {}", orderDto.toString(), customerId);
        return ResponseEntity.ok().body(query.createOrders(orderDto,customerId));
    }

}
