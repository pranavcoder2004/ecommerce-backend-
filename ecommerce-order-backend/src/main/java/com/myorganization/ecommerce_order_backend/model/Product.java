package com.myorganization.ecommerce_order_backend.model;

import com.myorganization.ecommerce_order_backend.model.enums.Rating;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prdId;

    private String productName;
    private Integer productQuantity;
    private Double pricefor1;
    private String productSeller;

    private String prdDescription;
    private String prdSellerEMail;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserCart cart;
}

