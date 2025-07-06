package com.myorganization.ecommerce_order_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Data
@Table(name="usercart")
public class UserCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(mappedBy = "cart")
    private User userId;
    @OneToMany
    private List<Product> products;
    @OneToMany(mappedBy = "cart")
    private List<Order> orderd;

}
