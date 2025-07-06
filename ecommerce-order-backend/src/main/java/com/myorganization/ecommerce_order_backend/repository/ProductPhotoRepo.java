package com.myorganization.ecommerce_order_backend.repository;

import com.myorganization.ecommerce_order_backend.model.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPhotoRepo extends JpaRepository<ProductPhoto,Long> {
    ProductPhoto findByorderId(Long id);

}
