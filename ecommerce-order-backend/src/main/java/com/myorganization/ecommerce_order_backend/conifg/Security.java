package com.myorganization.ecommerce_order_backend.conifg;

import com.myorganization.ecommerce_order_backend.model.enums.Role;
import com.myorganization.ecommerce_order_backend.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {
    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
      return   httpSecurity.authorizeHttpRequests(auth->
                auth
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/products/name/**").permitAll()
                        .requestMatchers("/products/get_usename/**").permitAll()
                        .requestMatchers("/order/**").authenticated()
                        .requestMatchers("/products/add").hasRole(String.valueOf(Role.DUKANWALA))
                        .requestMatchers("/order/confirmation/**").hasRole(String.valueOf(Role.DUKANWALA))
                        .requestMatchers("/order/cancelby").hasRole(String.valueOf(Role.DUKANWALA))
                        .requestMatchers("/order/cancelby").hasRole(String.valueOf(Role.DELIVERY))
                        .requestMatchers("/delivery/finding").hasRole(String.valueOf(Role.DELIVERY))



                        .requestMatchers("/server/health").permitAll()

                        .anyRequest().authenticated()

              )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)//.csrf(csrf->csrf.disable())
              .build();
    }

    public void ConfigureGlobal(AuthenticationManagerBuilder hs) throws Exception{
        hs.userDetailsService(userAuthenticationService)
                .passwordEncoder(passwordMatcher());

    }


@Bean
public PasswordEncoder passwordMatcher(){
    return new BCryptPasswordEncoder();
}}
