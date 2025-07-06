package com.myorganization.ecommerce_order_backend.service;

import com.myorganization.ecommerce_order_backend.model.Order;
import com.myorganization.ecommerce_order_backend.model.ProductPhoto;
import com.myorganization.ecommerce_order_backend.model.enums.OrderStatus;
import com.myorganization.ecommerce_order_backend.repository.OrderRepo;
import com.myorganization.ecommerce_order_backend.repository.ProductPhotoRepo;
import com.myorganization.ecommerce_order_backend.service.redis.RedisService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UploadProductPhoto {
    @Autowired
    ProductPhotoRepo productPhotoRepo;
    @Autowired
    RedisService redisService;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    MailsenderService mailsenderService;

    private Long id;

    @Transactional
    public String uploadPhoto(Long orId, MultipartFile multipartFile){
        id = orId;

      try {
          ProductPhoto productPhoto = new ProductPhoto();

          productPhoto.setPhoto(multipartFile.getBytes());
          productPhoto.setOrderId(orId);
          ProductPhoto pc = productPhotoRepo.save(productPhoto);
          if (pc.getPhoto() != null) {
              Order o = orderRepo.findById(orId).orElse(null);
              generateOtp(orId);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }




        return "uploaded and otp genrated redirecting to next page";
    }


    public String orderConfirmation(Integer otp){
        int a = redisService.getRedis(id, Integer.class);
        if(a!=otp){
            return "invalid otp generate again";
        }
        else {
            Order o = orderRepo.findById(id).orElse(null);
            o.setStatus(OrderStatus.DELIVERED);
            o.setLastUpdated(LocalDateTime.now());
            mailsenderService.sendMail("Thanks for shopping from tanujCart ","order Deliverd",o.getCustomerEmail());
            return "done";
        }

    }
    public Optional<String> generateOtp(long ord){
        ProductPhoto p= productPhotoRepo.findByorderId(ord);
        Order o =orderRepo.findById(ord).orElse(null);
        if(p.getPhoto()!=null){


           Random random =new Random();
        Integer no =random.nextInt(1000,9999);

        mailsenderService.sendMail("your otp is "+ no + "it is valid for only 60 sec do not share","Confirmation otp",o.getCustomerEmail() );
        redisService.setRedis(String.valueOf(ord),no,90l);
        return Optional.of("value genrated again now call the verification otp api again");

    }
        return Optional.of("photo is not uploaded pls check it and uploaded it again");
    }

}
