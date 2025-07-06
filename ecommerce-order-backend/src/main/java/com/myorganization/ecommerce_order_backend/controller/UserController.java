package com.myorganization.ecommerce_order_backend.controller;

import com.myorganization.ecommerce_order_backend.dto.request.UserRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.ProductResponseDto;
import com.myorganization.ecommerce_order_backend.dto.response.UserResponseDto;
import com.myorganization.ecommerce_order_backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<>(userService.userAccountCreation(userRequestDto), HttpStatusCode.valueOf(200));

    }

  @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.userAccountDeletion(id), HttpStatusCode.valueOf(200));
  }
  @PutMapping("/update")
    public ResponseEntity<String>updateUser(@RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<>(userService.userAccountUpdation(userRequestDto),HttpStatusCode.valueOf(200));
  }
  @GetMapping("get/{id}")
    public ResponseEntity<UserResponseDto>getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserDetail(id),HttpStatusCode.valueOf(200));
  }

}
