package com.myorganization.ecommerce_order_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    @NotBlank(message = "Product should have a valid name that is easy to find")
    @JsonProperty("productName")
    private String productName;

    @Min(value = 1,message = "At should have One quantity")

    private Integer productQuantity;
    @NotNull(message = "price is important")
    @Positive(message = "price must be positive")
    private Double priceFor1;
    @NotNull(message = "it should have the proper name and location opf the seller")
    private String productSeller;
    @NotNull
    @Email(message = "it should be valid email")
    private String sellerEmail;

    private String prdDescription;

    private MultipartFile prdphoto;
}
