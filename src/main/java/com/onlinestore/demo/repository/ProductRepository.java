package com.onlinestore.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlinestore.demo.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    Optional<Product> findByProductName(String productName);
}