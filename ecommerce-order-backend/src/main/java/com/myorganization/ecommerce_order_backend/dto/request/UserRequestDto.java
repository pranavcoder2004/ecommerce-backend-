package com.myorganization.ecommerce_order_backend.dto.request;

import com.myorganization.ecommerce_order_backend.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.PartitionOffset;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private String username;
    private String password;
    private Role role;
}
