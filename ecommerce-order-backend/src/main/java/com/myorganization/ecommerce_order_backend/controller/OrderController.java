package com.myorganization.ecommerce_order_backend.controller;

import com.myorganization.ecommerce_order_backend.dto.request.OrderRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.OrderResponseDto;
import com.myorganization.ecommerce_order_backend.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/place_order")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestDto requestDto){
        return new ResponseEntity<>(orderService.placeOrder(requestDto), HttpStatusCode.valueOf(200));
    }
    @GetMapping("/get-detail/{id}")
    public ResponseEntity<OrderResponseDto> getDetail(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getOrderDetail(id),HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(orderService.cancelOrder(id),HttpStatusCode.valueOf(200));
    }
    @PutMapping("/confirmation")
    public ResponseEntity<String> orderConfirmation(@RequestParam Long id){
        return new ResponseEntity<>(orderService.orderConfirmation(id),HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/cancelby")
    public ResponseEntity<String> ordeCancelationbyDeLiveryOrSeller(@RequestParam Long id,@RequestParam String cancelBy,@RequestParam String reason){
         return new ResponseEntity<>(orderService.orderCancelation(id,cancelBy,reason),HttpStatusCode.valueOf(200));
    }


}
