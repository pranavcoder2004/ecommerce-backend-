package com.myorganization.ecommerce_order_backend.dto.response;

import com.myorganization.ecommerce_order_backend.model.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;


    private Long customerId;


    private Long productId;


    private Integer quantity;


    @Enumerated(EnumType.STRING)
    private OrderStatus status; // e.g., PENDING, CONFIRMED, SHIPPED, CANCELLED

    private Double totalAmount;

    private String customerEmail;

    private LocalDateTime orderDate;

    private LocalDateTime lastUpdated;

    // Redis/Kafka won't store this, but DB will
    private boolean paymentConfirmed;

    // Security context or tracking
    private String createdBy;

}
