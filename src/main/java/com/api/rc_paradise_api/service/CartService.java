package com.api.rc_paradise_api.service;

import com.api.rc_paradise_api.model.Cart;
import com.api.rc_paradise_api.model.Product;
import com.api.rc_paradise_api.model.User;
import com.api.rc_paradise_api.repository.CartRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    // Dependency Injection using constructor
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
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
}