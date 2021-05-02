package com.api.rc_paradise_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long productId;
    private String sellerID;
    private String name;
    private String description;
    private double price;
    private int qty;
    private String category;
    private String manufacturer;
    private int rDistance;
    @Lob
    private byte[] image;

}
