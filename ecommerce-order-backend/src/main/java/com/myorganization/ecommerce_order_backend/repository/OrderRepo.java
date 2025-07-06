package com.myorganization.ecommerce_order_backend.repository;


import com.myorganization.ecommerce_order_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query("SELECT p FROM Order p WHERE p.address LIKE %:address% OR p.address LIKE %:address%")
    List<Order> findByorderAddress(@Param("address") String address);

}