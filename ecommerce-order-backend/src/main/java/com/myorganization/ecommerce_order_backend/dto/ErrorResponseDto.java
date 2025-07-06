package com.myorganization.ecommerce_order_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@NoArgsConstructor
@Data
public class ErrorResponseDto {
    private String message;
    private LocalTime localTime;
    private String disp;
    public ErrorResponseDto(String message,LocalTime localTime,String disp){
        this.message=message;
        this.localTime=localTime;
        this.disp=disp;
    }
}
