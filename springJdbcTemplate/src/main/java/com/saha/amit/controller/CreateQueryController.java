package com.saha.amit.controller;

import com.saha.amit.AppConstants;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProductDto;
import com.saha.amit.repository.CreateQueryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("create")
public class CreateQueryController {

    Logger logger = LoggerFactory.getLogger(CreateQueryController.class);

    private final CreateQueryRepository query;

    @Autowired
    public CreateQueryController(CreateQueryRepository query) {
        this.query = query;
    }


    @Operation(summary = "Insert new customer and their profile", description = AppConstants.CREATE_CUSTOMER_DESCRIPTION)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Customer successfully inserted"), @ApiResponse(responseCode = "400", description = "Invalid request data"), @ApiResponse(responseCode = "500", description = "Server error during insertion")})
    @PutMapping("customer")
    public ResponseEntity<Long> insertCustomer(@RequestBody CustomerDto customerDto) {
        logger.info("Insert Customer in DB, {}", customerDto.toString());
        return ResponseEntity.ok().body(query.insertCustomer(customerDto));
    }

    @Operation(summary = "Add product & categories", description = AppConstants.CREATE_PRODUCT_DESCRIPTION)
    @PutMapping("product")
    public ResponseEntity<Long> addProductWithCategories(@RequestBody ProductDto productDto) {
        logger.info("Add product productDto: {}", productDto);
        return ResponseEntity.ok().body(query.addProductWithCategories(productDto.getName(), productDto.getPrice(), productDto.getCategoryIds()));
    }

    @Operation(summary = "Add product with categories", description = "Creates a product and links it with given category IDs")
    @PostMapping("product2")
    public ResponseEntity<Long> addProductWithCategories2(@Valid @RequestBody ProductDto productDto) {
        logger.info("Add product using NamedParameterJdbcTemplate productDto: {}", productDto);
        Long productId = query.addProductWithCategories2(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }


    @Operation(summary = "Create order, Payments", description = AppConstants.CREATE_ORDER_DESCRIPTION)
    @PutMapping("order/{customerId}")
    public ResponseEntity<String> createOrders(@PathVariable long customerId, @RequestBody OrderDto orderDto) {
        logger.info("createOrders order data: {} , customer id: {}", orderDto.toString(), customerId);
        return ResponseEntity.ok().body(query.createOrders(orderDto, customerId));
    }

}
