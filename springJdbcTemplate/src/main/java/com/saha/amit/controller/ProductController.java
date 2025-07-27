package com.saha.amit.controller;

import com.saha.amit.dto.ProductDto;
import com.saha.amit.repository.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    Log log = LogFactory.getLog(ProductController.class);

    @GetMapping("/getProductCount")
    public Integer getProductCount(){
        return  productRepository.getProductCount();
    }


    @GetMapping("/getProductNameByUuid/{productUuid}")
    public String getProductNameByUuid(@PathVariable int productUuid){
        return  productRepository.getProductNameByUuid(productUuid);
    }


    @GetMapping("/getProductByUuid/{productUuid}")
    public ProductDto getProductByUuid(@PathVariable int productUuid){
        return  productRepository.getProductByUuid(productUuid);
    }

}
