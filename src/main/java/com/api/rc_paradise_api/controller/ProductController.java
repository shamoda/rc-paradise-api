package com.api.rc_paradise_api.controller;

import com.api.rc_paradise_api.model.Product;
import com.api.rc_paradise_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@RequestParam("sellerID") String sellerID,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") double price,
                                        @RequestParam("qty") int qty,
                                        @RequestParam("category") String category,
                                        @RequestParam("manufacturer") String manufacturer,
                                        @RequestParam("rDistance") int rDistance,
                                        @RequestParam("image") MultipartFile image
                                        )
    {
        Product p = new Product();
        try {
            p.setSellerID(sellerID);
            p.setName(name);
            p.setDescription(description);
            p.setPrice(price);
            p.setQty(qty);
            p.setCategory(category);
            p.setManufacturer(manufacturer);
            p.setRDistance(rDistance);
            p.setImage(image.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(service.saveProduct(p), HttpStatus.CREATED);
    }

    @PutMapping("/product/update")
    public ResponseEntity<?> updateProduct(@RequestParam("productId") Long productId,
                                           @RequestParam("sellerID") String sellerID,
                                           @RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("price") double price,
                                           @RequestParam("qty") int qty,
                                           @RequestParam("category") String category,
                                           @RequestParam("manufacturer") String manufacturer,
                                           @RequestParam("rDistance") int rDistance,
                                           @RequestParam("image") MultipartFile image
                                            )
    {
        Product p = new Product();
        try {
            p.setProductId(productId);
            p.setSellerID(sellerID);
            p.setName(name);
            p.setDescription(description);
            p.setPrice(price);
            p.setQty(qty);
            p.setCategory(category);
            p.setManufacturer(manufacturer);
            p.setRDistance(rDistance);
            p.setImage(image.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(service.saveProduct(p), HttpStatus.OK);
    }


    @GetMapping("/product")
    public ResponseEntity<?> retrieveAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/search")
    public ResponseEntity<?> searchProducts(@RequestParam(value = "query") String query) {
        return new ResponseEntity<>(service.searchProducts(query), HttpStatus.OK);
    }

    @GetMapping("/product/seller")
    public ResponseEntity<?> searchProductsBySeller(@RequestParam(value = "sellerId") String sellerId, @RequestParam(value = "query") String query) {
        return new ResponseEntity<>(service.searchProductsBySeller(sellerId, query), HttpStatus.OK);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(service.getProduct(productId), HttpStatus.OK);
    }


    @DeleteMapping("/product/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(service.deleteProduct(productId), HttpStatus.OK);
    }

}
