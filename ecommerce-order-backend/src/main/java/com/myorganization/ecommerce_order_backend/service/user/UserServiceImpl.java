package com.myorganization.ecommerce_order_backend.service.user;


import com.myorganization.ecommerce_order_backend.appCache.AppCacheForUserNameUnique;
import com.myorganization.ecommerce_order_backend.dto.request.UserRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.UserResponseDto;
import com.myorganization.ecommerce_order_backend.model.User;
import com.myorganization.ecommerce_order_backend.model.UserCart;
import com.myorganization.ecommerce_order_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AppCacheForUserNameUnique appCacheForUserNameUnique;
    @Override
    public String userAccountCreation(UserRequestDto userRequestDto) {
        User u = new User();
        if(appCacheForUserNameUnique.getUt().contains(userRequestDto.getUsername())){
            return "username already exists pls use a different user name ";
        }
        else{
            UserCart uc =new UserCart();

         u =covertDtoToUser(userRequestDto,u);
         uc.setUserId(u);
         u.setCart(uc);
        userRepo.save(u);
        return "user Created successfully  with id =   "+u.getId();}

    }

    @Override
    public String userAccountDeletion(Long id) {

        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User u = userRepo.findByUsername(name);
        if(u!=null && id == u.getId()){
            userRepo.deleteById(id);
            return "user with id  =  "+id+"   deleted successfully";

        }


            return "user does not Exist";


    }

    @Override
    public String userAccountUpdation(UserRequestDto userRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u= userRepo.findByUsername(authentication.getName());
        if(u!=null){
        u = covertDtoToUser(userRequestDto,u);
        userRepo.save(u);
        return "user update successfully with id  "+ u.getId();}
        return "user not exists!!!";



    }

    @Override
    public UserResponseDto getUserDetail(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
       User u = userRepo.findById(id).orElse(null);
       User u2 =userRepo .findByUsername(name);
       if(u!=null && u2.getId()==id){
           return UserResponseDto.builder().id(u.getId()).username(u.getUsername()).role(u.getRole()).build();
       }
       return null;

    }
    //Helper method
    //convert dto to user
    User covertDtoToUser(UserRequestDto userRequestDto,User u){

        u.setUsername(userRequestDto.getUsername());
        u.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        u.setRole(userRequestDto.getRole());
        return u;
    }
}
