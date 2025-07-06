package com.myorganization.ecommerce_order_backend.service.user;

import com.myorganization.ecommerce_order_backend.dto.request.UserRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.UserResponseDto;

public interface UserService {
    String userAccountCreation(UserRequestDto userRequestDto);
    String userAccountDeletion(Long id);
   String userAccountUpdation(UserRequestDto userRequestDto);
   UserResponseDto getUserDetail(Long id);


}
