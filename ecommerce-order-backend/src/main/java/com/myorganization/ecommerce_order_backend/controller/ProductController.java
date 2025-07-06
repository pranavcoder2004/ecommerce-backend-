package com.myorganization.ecommerce_order_backend.controller;

import com.myorganization.ecommerce_order_backend.dto.request.ProductRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.ProductResponseDto;
import com.myorganization.ecommerce_order_backend.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/name")

    public ResponseEntity<Page<ProductResponseDto>> getPage(
                                                            @RequestParam(defaultValue = "asc") String grp,
                                                            @RequestParam(defaultValue = "prdId") String sortby,
                                                            @RequestParam(defaultValue = "0") Integer pageno,
                                                            @RequestParam(defaultValue = "10") Integer size) {
        return new ResponseEntity<>(productService.getProductsName(sortby,grp,size,pageno), HttpStatusCode.valueOf(200));
    }


    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.addProduct(productRequestDto), HttpStatusCode.valueOf(200));
    }
    @GetMapping("/get_usename")
    public ResponseEntity<List<ProductResponseDto>> getList(@RequestParam("keyword") String name){
        return new ResponseEntity<>(productService.getProductsName(name),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponseDto> getPdById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductId(id), HttpStatusCode.valueOf(200));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto>updateProduct(@PathVariable Long id,@RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<>(productService.UpdateProduct(id,productRequestDto),HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delteProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.removeProduct(id),HttpStatusCode.valueOf(200));
    }



}