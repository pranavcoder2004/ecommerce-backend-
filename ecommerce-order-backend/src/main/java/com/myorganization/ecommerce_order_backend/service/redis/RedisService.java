package com.myorganization.ecommerce_order_backend.service.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myorganization.ecommerce_order_backend.dto.response.OrderResponseDto;
import com.myorganization.ecommerce_order_backend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    public void setRedis(String key ,Object o,Long time){
        redisTemplate.opsForValue().set(key,o,445, TimeUnit.SECONDS);
    }

    public <T> T getRedis(Long id, Class<T> orderResponseDtoClass) {
        try{
            Object o= redisTemplate.opsForValue().get(id);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(),orderResponseDtoClass);
            }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
