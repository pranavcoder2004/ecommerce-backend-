package com.myorganization.ecommerce_order_backend.dto.request;

import com.myorganization.ecommerce_order_backend.model.enums.PaymentMode;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @NotBlank(message = "product id cant be blank")
private Long productId;
    @Min(value=1,message = "cant be less then one")
private Long quantity;
    @NotBlank(message = "address should be complete with pin code as well")
private String shippingAddress;

private PaymentMode mode;
@Email(message = "enter a valid email address")
private String email;
@Pattern(regexp ="^[0-9]{10}$", message = "Phone number must be 10 digits")
private String phone_no;


}
