package com.myorganization.ecommerce_order_backend.service.order;

import com.myorganization.ecommerce_order_backend.dto.request.OrderRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
     String placeOrder(OrderRequestDto orderRequestDto);
     OrderResponseDto getOrderDetail(Long id);
     String cancelOrder(Long id);
     String orderConfirmation(Long orderId);
     String orderCancelation(Long orderId,String cancelBy, String reason);



}
