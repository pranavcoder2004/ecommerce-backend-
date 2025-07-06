package com.myorganization.ecommerce_order_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerHealth {
    @GetMapping("/health")
            public String checkserver(){
                return "server is Live!!";
    }
}
