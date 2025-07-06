package com.myorganization.ecommerce_order_backend.Exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message){
        super(message);
    }

}
