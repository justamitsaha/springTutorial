package com.saha.amit.repository;

import com.saha.amit.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.categories")
    List<Product> findAllDistinctWithCategories();

    @Query(value = "SELECT DISTINCT p.product_uuid, p.name, p.price, c.category_uuid, c.name AS categoryName " +
            "FROM Product p " +
            "LEFT JOIN product_category pc ON p.product_uuid = pc.product_id " +
            "LEFT JOIN Category c ON pc.category_id = c.category_uuid",
            nativeQuery = true)
    List<Object[]> findAllDistinctWithCategoriesNative();
}

