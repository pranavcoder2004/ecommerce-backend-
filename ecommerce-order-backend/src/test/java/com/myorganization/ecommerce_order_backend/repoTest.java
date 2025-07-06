package com.myorganization.ecommerce_order_backend;

import com.myorganization.ecommerce_order_backend.model.User;
import com.myorganization.ecommerce_order_backend.model.enums.Role;
import com.myorganization.ecommerce_order_backend.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class repoTest {
    @Autowired
    UserRepo userRepo;
    @Test
    public void Testing(){
        User user =new User();
        user.setId(1l);
        user.setUsername("tanuj");
        user.setPassword("$2a$10$Wsi0KABDWpE1pwe5VMo45uwaFfcrolRcRQ6RGaEszv4.U31.uXRUC");
        user.setRole(Role.DUKANWALA);
        Assertions.assertEquals(user,userRepo.findByUsername("tanuj"));
    }
}
