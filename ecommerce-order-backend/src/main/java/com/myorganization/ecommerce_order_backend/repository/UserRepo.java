package com.myorganization.ecommerce_order_backend.repository;

import com.myorganization.ecommerce_order_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {



    User findByUsername(String username);
}
