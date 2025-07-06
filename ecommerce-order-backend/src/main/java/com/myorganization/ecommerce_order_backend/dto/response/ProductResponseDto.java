package com.myorganization.ecommerce_order_backend.dto.response;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private Long prdId;
    private String productName;
    private Integer productQuantity;
    private Double pricefor1;
    private String productSeller;

    private String prdDescription;


}
