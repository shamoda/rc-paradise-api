package com.api.rc_paradise_api.repository;

import com.api.rc_paradise_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // fetch products according to the query param
    @Query("from Product p WHERE p.name LIKE %:searchText% OR p.category LIKE %:searchText% ORDER BY p.price")
    List<Product> searchQuery(@Param("searchText") String searchText);

    // fetch products of a particular seller and search query
    @Query("from Product p WHERE p.sellerID = :sellerId AND p.name LIKE %:query% ORDER BY p.price")
    List<Product> searchProductsBySeller(@Param("sellerId") String sellerId, @Param("query") String query);
}
