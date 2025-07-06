package com.myorganization.ecommerce_order_backend.repository;

import com.myorganization.ecommerce_order_backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword%")
    List<Product> findByProductName(@Param("keyword") String keyword);





}
