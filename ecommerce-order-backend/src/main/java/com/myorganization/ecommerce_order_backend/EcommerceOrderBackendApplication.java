package com.myorganization.ecommerce_order_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EcommerceOrderBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceOrderBackendApplication.class, args);
	}

}
