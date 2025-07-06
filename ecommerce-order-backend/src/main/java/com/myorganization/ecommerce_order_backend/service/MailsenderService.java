package com.myorganization.ecommerce_order_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
@Service
public class MailsenderService {
    @Autowired
    JavaMailSender javaMailSender;
    @GetMapping
    public void sendMail(String text,String subject,String to){
        try{
            SimpleMailMessage sm = new SimpleMailMessage();
            sm.setText(text);
            sm.setSubject(subject);
            sm.setTo(to);
            javaMailSender.send(sm);

        } catch (Exception e) {
            e.printStackTrace();

        }
}}
