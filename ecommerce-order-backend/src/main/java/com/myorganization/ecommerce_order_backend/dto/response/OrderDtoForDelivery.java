package com.myorganization.ecommerce_order_backend.dto.response;

import lombok.Data;

@Data
public class OrderDtoForDelivery {
    private String customerName;
    private String address;
    private String contactNo;
    private Long orderId;
    private Double price;
}
