package com.saha.amit.controller;

import com.saha.amit.dto.ProductDto;
import com.saha.amit.dto.ProfileDto;
import com.saha.amit.repository.SimpleQueryRepository;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("simpleGet")
public class SimpleQueryController {

    private final SimpleQueryRepository simpleQueryRepository;

    @Autowired
    public SimpleQueryController(SimpleQueryRepository simpleQueryRepository) {
        this.simpleQueryRepository = simpleQueryRepository;
    }


    @Operation(summary = "Query for INTEGER searches PRODUCT COUNT")
    @GetMapping("1/getInt")
    public Integer getProductCount() {
        return simpleQueryRepository.getProductCount();
    }

    @Operation(summary = "Query for STRING returns PRODUCT name")
    @GetMapping("2/getString/{id}")
    public String getProductNameByUuid(@PathVariable int id) {
        return simpleQueryRepository.getProductNameByUuid(id);
    }

    @Operation(summary = "Query for custom object PRODUCT using ROW_MAPPER")
    @GetMapping("3/getProduct/{id}")
    public ProductDto getProductByUuid(@PathVariable int id) {
        return simpleQueryRepository.getProductByUuid(id);
    }

    @Operation(summary = "Query for PROFILE by id using inline row mapper")
    @GetMapping("4/getProfileByIds")
    public ProfileDto findCustomerProfileById(@PathVariable long id) {
        return simpleQueryRepository.findCustomerProfileById(id);
    }

    @Operation(summary = "Perform IN QUERY to fetch count of product where INPUT is LIST<ID>")
    @GetMapping("5/getProductsByIds")
    public Integer productInProductId(@RequestParam List<Long> productIdList) {
        return simpleQueryRepository.productInProductId(productIdList);
    }


    @Operation(summary = "LIST<PRODUCTS> using inline row mapper")
    @GetMapping("6/getAllProducts")
    public List<ProductDto> getAllProducts() {
        return simpleQueryRepository.getAllProducts();
    }
}
