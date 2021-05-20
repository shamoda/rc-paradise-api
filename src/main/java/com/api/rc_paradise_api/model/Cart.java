package com.api.rc_paradise_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//Cart model
public class Cart {

    @Id
    private String pID;
    private String productID;
    private String buyerName;
    private String name;
    private String price;
    private String deliveryMode;
    private String sellerId;
    private String buyerAddress;
    private String quantity;

}
