package com.myorganization.ecommerce_order_backend.service.order;

import com.myorganization.ecommerce_order_backend.Exception.ProductNotFound;
import com.myorganization.ecommerce_order_backend.dto.request.OrderRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.OrderResponseDto;
import com.myorganization.ecommerce_order_backend.model.Order;
import com.myorganization.ecommerce_order_backend.model.Product;
import com.myorganization.ecommerce_order_backend.model.User;
import com.myorganization.ecommerce_order_backend.model.UserCart;
import com.myorganization.ecommerce_order_backend.model.enums.OrderStatus;
import com.myorganization.ecommerce_order_backend.model.enums.PaymentMode;
import com.myorganization.ecommerce_order_backend.repository.OrderRepo;
import com.myorganization.ecommerce_order_backend.repository.ProductRepo;
import com.myorganization.ecommerce_order_backend.repository.UserRepo;
import com.myorganization.ecommerce_order_backend.service.MailsenderService;
import com.myorganization.ecommerce_order_backend.service.redis.RedisService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo repo;
    @Autowired
    MailsenderService mailsenderService;

    @Autowired
    RedisService redisService;
   
    @Autowired
    UserRepo userRepo;
    @Override
    public String placeOrder(OrderRequestDto orderRequestDto) {
        Order r =convertDtoToOrder(orderRequestDto);
        UserCart uc =new UserCart();
        uc.setOrderd(List.of(r));
        r.setCart(uc);


         r =orderRepo.save(r);

        return "Order is succesfully Placed your id =" +r.getId();
        
    }

    @Override
    public OrderResponseDto getOrderDetail(Long id) {

        OrderResponseDto redis = redisService.getRedis(id,OrderResponseDto.class);
        if(redis !=null){
            return redis;
        }
        else {
        Order r = orderRepo.findById(id).orElse(null);
        User u =userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if(r!=null&& r.getCustomerId().equals(u.getId())){

            return covertOrderToDto(r);
        }
        else throw new ProductNotFound("order does not exists or not done by u  "+ id);}


    }

    @Override
    public String cancelOrder(Long id) {
        User u =userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Order o = orderRepo.findById(id).orElse(null);
        if(o!= null && u.getId()==o.getId()){

            orderRepo.delete(o);
            return "Product deleted " + id + "payment will be received in 7 working days";
        }
        else {
            throw new ProductNotFound("error in deleting the object");
        }
    }


    //Helper method to convert RequestOrderDto to Order Model
    Order convertDtoToOrder(OrderRequestDto orderRequestDto){
        Product p = repo.findById(orderRequestDto.getProductId()).orElse(null);
        Integer c = p.getProductQuantity();
        String sldBy = p.getProductSeller();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User u =userRepo.findByUsername(name);
        if(p==null){
            throw new ProductNotFound("Product that u are trying to buy does not exists"+orderRequestDto.getProductId());
        }
        else if(orderRequestDto.getQuantity()>c){
            throw new ProductNotFound("Product u are trying to buy either out of stock or not availabe in given quantity :orderQuantity = "+orderRequestDto.getQuantity()+" StockAvailable = "+p.getProductQuantity());
        }
       Double price =p.getPricefor1();
        Boolean payment;
        if(orderRequestDto.getMode()!=PaymentMode.CASH){
             payment = paymentConfirm(orderRequestDto.getMode(),price,orderRequestDto.getEmail(),p.getPrdSellerEMail());

        }
        else {
            payment =false;
        }
        return Order.builder().orderDate(LocalDateTime.now()).address(orderRequestDto.getShippingAddress())
                .customerEmail(orderRequestDto.getEmail())
                .lastUpdated(LocalDateTime.now()).status(OrderStatus.PENDING)
                .totalAmount(orderRequestDto.getQuantity()*price)
                .customerId(u.getId())
                .paymentMode(orderRequestDto.getMode())
                .productId(orderRequestDto.getProductId())
                .SoledBy(sldBy)
                .no(orderRequestDto.getPhone_no())

                .paymentConfirmed(payment)
                .quantity(orderRequestDto.getQuantity())
                .build();
    }

    @Override
    @Transactional
    public String orderConfirmation(Long orderId) {

        Order o = orderRepo.findById(orderId).orElse(null);
        Long pdId = o.getProductId();
        Product p = repo.findById(pdId).orElse(null);
        o.setStatus(OrderStatus.CONFIRMED);
        o.setLastUpdated(LocalDateTime.now());
        p.setProductQuantity(Math.toIntExact( p.getProductQuantity() - o.getQuantity()));
        repo.save(p);
        orderRepo.save(o);
        mailsenderService.sendMail("your order with order id = "+ orderId + "id confirmed","order confirmed",o.getCustomerEmail());
        return "order confirmed";
    }

    @Transactional
    boolean paymentConfirm(PaymentMode pay,Double price,String email,String selleremail) {

        Double accountBalance = 1100.0;
        PaymentMode hello =PaymentMode.CASH;
        if(accountBalance<price ){
           mailsenderService.sendMail(" Transaaction failed due to unavoidable circumstances contact your bank","Payment fail",email);


                return false;


        }
        else {


            mailsenderService.sendMail("\"payment of rupee = \" +price +\" debited,","payment debited",email);
            mailsenderService.sendMail("pls confirm the product and by cliking on the api localhost/order/cofirm","orderconifmation from the seller",selleremail);


            return true;




        }

    }

    @Override
    public String orderCancelation(Long orderId, String cancelBy, String reason) {
        Order o = orderRepo.findById(orderId).orElse(null);
        Product p =repo.findById(o.getId()).orElse(null);
        p.setProductQuantity(Math.toIntExact(p.getProductQuantity() + o.getQuantity()));
        orderRepo.delete(o);
        mailsenderService.sendMail("we are sorry for the in convinience but ur order with orderid ="+orderId + "  is cancelled by our "+ cancelBy +"  due to "+reason ,"order canceled!",o.getCustomerEmail());
        return "cancelled";
    }

    //Helper method to convert Product to Dto
    OrderResponseDto covertOrderToDto(Order r) {
    return OrderResponseDto.builder().orderDate(r.getOrderDate())
            .id(r.getId())
            .customerEmail(r.getCustomerEmail())
            .lastUpdated(r.getLastUpdated())
            .paymentConfirmed(r.isPaymentConfirmed())
            .productId(r.getProductId())
            .status(r.getStatus())
            .totalAmount(r.getTotalAmount())
            .quantity(Math.toIntExact(r.getQuantity()))
            .customerId(r.getCustomerId())
            .build();
    }
}
