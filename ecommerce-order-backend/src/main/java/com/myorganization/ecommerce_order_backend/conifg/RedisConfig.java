package com.myorganization.ecommerce_order_backend.conifg;

import org.apache.kafka.clients.admin.ClientMetricsResourceListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    RedisTemplate redisTemplate(RedisConnectionFactory rc){
        RedisTemplate rs = new RedisTemplate<>();
        rs.setConnectionFactory(rc);
        rs.setKeySerializer(new StringRedisSerializer());
        rs.setKeySerializer(new StringRedisSerializer());
        return rs;
    }

}
