package com.myorganization.ecommerce_order_backend.model;

import com.myorganization.ecommerce_order_backend.model.enums.OrderStatus;
import com.myorganization.ecommerce_order_backend.model.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


    @Entity
    @Table(name = "orders")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        private Long customerId;


        private Long productId;


        private Long quantity;
        private String address;


        @Enumerated(EnumType.STRING)
        private OrderStatus status ; // e.g., PENDING, CONFIRMED, SHIPPED, CANCELLED

        private Double totalAmount;

        private String customerEmail;

        private LocalDateTime orderDate;

        private LocalDateTime lastUpdated;

        // Redis/Kafka won't store this, but DB will
        private boolean paymentConfirmed;
        @Enumerated(EnumType.STRING )
        private PaymentMode paymentMode;


        // Security context or tracking
        private String SoledBy;
        @ManyToOne(cascade = CascadeType.ALL)
        private UserCart cart;

        private String no;


    }

