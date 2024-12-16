package com.saha.amit.cotroller;

import com.saha.amit.model.Product;
import com.saha.amit.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    private final Log log = LogFactory.getLog(ProductController.class);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/distinct-with-categories")
    public ResponseEntity<List<Product>> getAllDistinctProductsWithCategories() {
        Random random = new Random();
        List<Product> products = null;
        if (random.nextBoolean()){
            log.info("Inside getAllDistinctProductsWithCategories");
            products = productService.getAllDistinctProductsWithCategories();
        } else {
            log.info("Inside getAllDistinctProductsWithCategoriesNative");
            products = productService.getAllDistinctProductsWithCategoriesNative();
        }

        return ResponseEntity.ok(products);
    }
}
