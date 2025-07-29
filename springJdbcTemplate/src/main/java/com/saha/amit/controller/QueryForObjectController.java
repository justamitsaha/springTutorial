package com.saha.amit.controller;

import com.saha.amit.dto.ProductDto;
import com.saha.amit.repository.QueryForObjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("object")
public class QueryForObjectController {

    @Autowired
    QueryForObjectRepository query;


    @Operation(summary = "Query for integer")
    @GetMapping("/getInt")
    public Integer getProductCount(){
        return  query.getProductCount();
    }

    @Operation(summary = "Query for string")
    @GetMapping("/getString/{id}")
    public String getProductNameByUuid(@PathVariable int id){
        return  query.getProductNameByUuid(id);
    }

    @Operation(summary = "Query for custom object Product")
    @GetMapping("/getProduct/{id}")
    public ProductDto getProductByUuid(@PathVariable int id){
        return  query.getProductByUuid(id);
    }

}
