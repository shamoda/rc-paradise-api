package com.api.rc_paradise_api.repository;

import com.api.rc_paradise_api.model.Cart;
import com.api.rc_paradise_api.service.CartService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CartRepository extends JpaRepository<Cart ,String> {





}
