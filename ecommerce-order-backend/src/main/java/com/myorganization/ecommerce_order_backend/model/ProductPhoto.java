package com.myorganization.ecommerce_order_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productphoto")
@Data
public class ProductPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Lob
    private byte[] photo;
    private Long orderId;

}
