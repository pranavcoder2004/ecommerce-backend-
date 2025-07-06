package com.myorganization.ecommerce_order_backend.controller;

import com.myorganization.ecommerce_order_backend.dto.response.OrderDtoForDelivery;
import com.myorganization.ecommerce_order_backend.model.ProductPhoto;
import com.myorganization.ecommerce_order_backend.service.UploadProductPhoto;
import com.myorganization.ecommerce_order_backend.service.delivery.ProductFindingByDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/delivery/finding")
public class DeliveryController {
    @Autowired
    ProductFindingByDelivery productFindingByDelivery;
    @Autowired
    UploadProductPhoto uploadProductPhoto;
    @GetMapping
    public ResponseEntity<List<OrderDtoForDelivery>> getall(@RequestParam("address")String address){
        return new ResponseEntity<>(productFindingByDelivery.findByAddress(address), HttpStatusCode.valueOf(200));
    }
    @PostMapping("/uploadphoto")
    public ResponseEntity<String> photo(@RequestParam Long orderId, @RequestParam MultipartFile multipartFile){
        return new ResponseEntity<>(uploadProductPhoto.uploadPhoto(orderId,multipartFile)+" go and call /delivey_conifmation",HttpStatusCode.valueOf(200));
    }
    @PostMapping("/delivey_conifmation")
    public ResponseEntity<String>otpConfirmation(@RequestParam Integer otp ){
        return new ResponseEntity<>(uploadProductPhoto.orderConfirmation(otp) +" if want to generate the otp again got to /get_otp ,by sending the ord id again",HttpStatusCode.valueOf(200));

    }
    @GetMapping("/get_otp")
    public ResponseEntity<Optional<String>>getOtP(@RequestParam Long ord){
    return new ResponseEntity<>(uploadProductPhoto.generateOtp(ord),HttpStatusCode.valueOf(200));
    }

}
