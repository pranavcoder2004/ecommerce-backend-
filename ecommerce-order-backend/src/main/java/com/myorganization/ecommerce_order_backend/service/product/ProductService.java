package com.myorganization.ecommerce_order_backend.service.product;


import com.myorganization.ecommerce_order_backend.dto.request.ProductRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto);
    String removeProduct(Long prdId);
    ProductResponseDto UpdateProduct(Long prdId,ProductRequestDto productRequestDto);
    Page<ProductResponseDto> getProductsName( String sortBy, String grpby, Integer size, Integer pageno);
    ProductResponseDto getProductId(Long id);
    List<ProductResponseDto>getProductsName(String name);



}
