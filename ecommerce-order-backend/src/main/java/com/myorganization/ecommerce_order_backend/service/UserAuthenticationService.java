package com.myorganization.ecommerce_order_backend.service;

import com.myorganization.ecommerce_order_backend.model.User;
import com.myorganization.ecommerce_order_backend.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserAuthenticationService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        log.info("Fetching user by username: {}", username);
         user = userRepo.findByUsername(username);
        log.error("not finding :{}",user.getId());
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name()) // very important
                .build();






}}
