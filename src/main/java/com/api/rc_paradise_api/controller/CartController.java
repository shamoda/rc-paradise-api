package com.api.rc_paradise_api.controller;

import com.api.rc_paradise_api.model.Cart;
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
    @GetMapping("/cart/{sellerId}")
    public ResponseEntity<?> getProduct(@PathVariable String sellerId) {
        return new ResponseEntity<>(cartService.searchCartBySeller(sellerId), HttpStatus.OK);
    }

    @PostMapping("/Add")
    public ResponseEntity<?> insertCart(@RequestBody Cart[] cart ){


        for(Cart item : cart){

            final UUID uuid = UUID.randomUUID();
            final String pID = uuid.toString();

            item.setPID(pID);

            cartService.saveCart(item);


        }

        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
