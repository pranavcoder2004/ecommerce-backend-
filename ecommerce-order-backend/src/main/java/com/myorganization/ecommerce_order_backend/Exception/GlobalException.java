package com.myorganization.ecommerce_order_backend.Exception;

import com.myorganization.ecommerce_order_backend.dto.ErrorResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorResponseDto> errorInUser(ProductNotFound er){
        String msg =er.getMessage();
        LocalTime lc =LocalTime.now();
        String disp ="error in the Product id";
        return new ResponseEntity<>(new ErrorResponseDto(msg,lc,disp), HttpStatusCode.valueOf(400));

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> ErrorInField(MethodArgumentNotValidException er){
        BindingResult br =er.getBindingResult();
        Map<String,String> map = new LinkedHashMap<>();
        for (FieldError e :br.getFieldErrors()){
            String a =e.getField();
            String b =e.getDefaultMessage();
           map.put(a,b);
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }
}
