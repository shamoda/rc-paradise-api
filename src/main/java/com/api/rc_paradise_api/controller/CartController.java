package com.api.rc_paradise_api.controller;

import com.api.rc_paradise_api.model.Cart;
import com.api.rc_paradise_api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CartController {


    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {

        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public ResponseEntity<?> getAllUsers() {

        return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK);
    }


    @PostMapping("/Add")
    public ResponseEntity<?> insertCart( @RequestParam String BuyerName){

//
//        for(Cart item : cart){
//
//            final UUID uuid = UUID.randomUUID();
//            final String pID = uuid.toString();
//
////            item.setDeliveryMode(DeliveryMode);
//
//
//            item.setPID(pID);
//            item.setBuyerName(BuyerName);
//            cartService.saveCart(item);


//        }

        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
