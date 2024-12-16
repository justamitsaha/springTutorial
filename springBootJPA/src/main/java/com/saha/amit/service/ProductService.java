package com.saha.amit.service;

import com.saha.amit.model.Category;
import com.saha.amit.model.Product;
import com.saha.amit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllDistinctProductsWithCategories() {
        return productRepository.findAllDistinctWithCategories();
    }

    public List<Product> getAllDistinctProductsWithCategoriesNative() {
        List<Object[]> results = productRepository.findAllDistinctWithCategoriesNative();
        Map<Long, Product> productMap = new HashMap<>();

        for (Object[] result : results) {
            Long productUuid = ((Number) result[0]).longValue();
            String productName = (String) result[1];
            Double productPrice = (Double) result[2];

            Product product = productMap.computeIfAbsent(productUuid, id -> {
                Product p = new Product();
                p.setProductUuid(id);
                p.setName(productName);
                p.setPrice(productPrice);
                p.setCategories(new ArrayList<>());
                return p;
            });

            if (result[3] != null) {
                Long categoryUuid = ((Number) result[3]).longValue();
                String categoryName = (String) result[4];

                Category category = new Category();
                category.setCategoryUuid(categoryUuid);
                category.setName(categoryName);

                product.getCategories().add(category);
            }
        }

        return new ArrayList<>(productMap.values());
    }
}
