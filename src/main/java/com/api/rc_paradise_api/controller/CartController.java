package com.api.rc_paradise_api.controller;

import com.api.rc_paradise_api.Emailservice.EmailService;
import com.api.rc_paradise_api.model.Cart;
import com.api.rc_paradise_api.model.MailRequest;
import com.api.rc_paradise_api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    private CartService cartService;
    private EmailService emailService;

    // Dependency Injection using constructor
    @Autowired
    public CartController(CartService cartService, EmailService emailService) {
        this.cartService = cartService;
        this.emailService = emailService;
    }

    @GetMapping("/cart") // retrieve all cart objects
    public ResponseEntity<?> getAllCart() {

        return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK);
    }

    @GetMapping("/cart/{sellerId}") // retrieve cart objects of a particular sellerID
    public ResponseEntity<?> getProduct(@PathVariable String sellerId) {
        return new ResponseEntity<>(cartService.searchCartBySeller(sellerId), HttpStatus.OK);
    }

    @PostMapping("/Add") // insert new record to the cart
    public ResponseEntity<?> insertCart(@RequestBody Cart[] cart ){
         //Looping through cart
        for(Cart item : cart){
            //Generating random purchase ID
            final UUID uuid = UUID.randomUUID();
            final String pID = uuid.toString();
            item.setPID(pID);
            //Saving the cart
            cartService.saveCart(item);

            Map<String, Object> model = new HashMap<>();
            //Sets the model
            model.put("ProductID", item.getProductID());
            model.put("ProductName",item.getName());
            model.put("Quantity", item.getQuantity());
            model.put("Price", item.getPrice());
            model.put("BuyerAdd",item.getBuyerAddress());
            //using the service method and sending email to delivery service based on each order
            cartService.handleMail(item.getDeliveryMode(), model);
        }
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
