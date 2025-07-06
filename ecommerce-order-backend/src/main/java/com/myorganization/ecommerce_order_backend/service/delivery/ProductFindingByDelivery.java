package com.myorganization.ecommerce_order_backend.service.delivery;

import com.myorganization.ecommerce_order_backend.dto.response.OrderDtoForDelivery;
import com.myorganization.ecommerce_order_backend.model.Order;
import com.myorganization.ecommerce_order_backend.model.enums.PaymentMode;
import com.myorganization.ecommerce_order_backend.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductFindingByDelivery {
    @Autowired
    OrderRepo orderRepo;
    public List<OrderDtoForDelivery>findByAddress(String address){
       List<Order> o= orderRepo.findByorderAddress(address);
       List<OrderDtoForDelivery> al =new ArrayList<>();
       for(Order r : o){
       OrderDtoForDelivery orderDtoForDelivery = new OrderDtoForDelivery();
       orderDtoForDelivery.setAddress(r.getAddress());
       orderDtoForDelivery.setOrderId(r.getId());
       if(r.getPaymentMode().equals(PaymentMode.CASH)){
       orderDtoForDelivery.setPrice(r.getTotalAmount());}
       orderDtoForDelivery.setPrice(0.0);
       orderDtoForDelivery.setContactNo(r.getNo());
       al.add(orderDtoForDelivery);
       }
return al;
    }
}
