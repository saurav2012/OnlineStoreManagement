package com.onlinestore.demo.repository;

import com.onlinestore.demo.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    Optional<Orders> findByTrackingNumber(String trackingNumber);
}
