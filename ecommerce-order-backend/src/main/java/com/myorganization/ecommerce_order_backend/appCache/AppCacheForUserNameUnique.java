package com.myorganization.ecommerce_order_backend.appCache;

import com.myorganization.ecommerce_order_backend.model.User;
import com.myorganization.ecommerce_order_backend.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Data
public class AppCacheForUserNameUnique {
    @Autowired
    UserRepo userRepo;


    List<String> ut=new ArrayList<>();
    @PostConstruct
    public void appCache(){
        List<User>l = userRepo.findAll();

        for (User u:l){
            ut.add(u.getUsername());
        }

    }





}
