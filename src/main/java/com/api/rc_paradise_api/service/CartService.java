package com.api.rc_paradise_api.service;

import com.api.rc_paradise_api.constant.EmailConstants;
import com.api.rc_paradise_api.model.Cart;
import com.api.rc_paradise_api.model.MailRequest;
import com.api.rc_paradise_api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final EmailService emailService;

    // Dependency Injection using constructor
    @Autowired
    public CartService(CartRepository cartRepository, EmailService emailService) {
        this.cartRepository = cartRepository;
        this.emailService = emailService;
    }

    // find all purchases
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    // insert new record
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // find orders by seller
    public List<Cart> searchCartBySeller(String sellerId) {
        return cartRepository.findBysellerId(sellerId);
    }

    //Handling the mail
    public void handleMail(String deliveryName , Map<String, Object> model) {

        String to;
        //Checking the Delivery service and selecting the mail to send
        if(deliveryName.equalsIgnoreCase(EmailConstants.UBER)){
              to = ("senathdonz@gmail.com");

        }else if(deliveryName.equalsIgnoreCase(EmailConstants.FEDEX)){  //Checking delivery service

              to = ("senathdonz@gmail.com");
        }else{

             to = ("senathdonz@gmail.com");

        }
        MailRequest mailRequest = new MailRequest(to,EmailConstants.FROM,deliveryName);
        emailService.sendEmail(mailRequest, model); //Sending the mail
    }
}