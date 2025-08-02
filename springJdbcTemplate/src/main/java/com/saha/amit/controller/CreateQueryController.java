package com.saha.amit.controller;

import com.saha.amit.AppConstants;
import com.saha.amit.dto.CustomerDto;
import com.saha.amit.dto.OrderDto;
import com.saha.amit.dto.ProductDto;
import com.saha.amit.repository.CreateQueryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @PutMapping("1/customer")
    public ResponseEntity<Long> insertCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(query.insertCustomer(customerDto));
    }

    @Operation(
            summary = "Add product with categories",
            description = AppConstants.CREATE_PRODUCT_DESCRIPTION
    )
//    @Parameters({
//            @Parameter(name = "productName", example = "Keyboard"),
//            @Parameter(name = "price", example = "49.99")
//    })
    @PostMapping("2/product")
    public ResponseEntity<Long> addProductWithCategories(@RequestBody ProductDto productDto) {
        logger.info("Add product productDto: {}", productDto);
        return ResponseEntity.ok().body(query.addProductWithCategories(productDto.getName(),
                productDto.getPrice(),
                productDto.getCategoryIds()));
    }

    @Operation(summary = AppConstants.CREATE_ORDER_DESCRIPTION)
    @PutMapping("3/order/{customerId}")
    public ResponseEntity<String> createOrders(@RequestBody OrderDto orderDto, @PathVariable long customerId) {
        logger.info("createOrders order data: {} , customer id: {}", orderDto.toString(), customerId);
        return ResponseEntity.ok().body(query.createOrders(orderDto, customerId));
    }

}
