package com.onlinestore.demo.service;

import com.onlinestore.demo.exception.AlreadyPresentException;
import com.onlinestore.demo.exception.NotFoundException;
import com.onlinestore.demo.model.Orders;
import com.onlinestore.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Orders> getAll(){
        return orderRepository.findAll();
    }

    public Orders getOrdersById(Long id){
        Optional<Orders> orders = orderRepository.findById(id);
        if(orders.isPresent()){
            return orders.get();
        }
        else
            throw new NotFoundException("Order with id "+ id+" not present");
    }

    public Orders save(Orders orders){
        Optional<Orders> presentOrders = orderRepository.findByTrackingNumber(orders.getTrackingNumber());
        if(presentOrders.isPresent()){
            throw new AlreadyPresentException("Order is already present with id " + orders.getId() );
        }
        return orderRepository.save(orders);
    }

    public Orders update(Orders orders){
        Optional<Orders> data = orderRepository.findById(orders.getId());
        if(data.isEmpty()){
            throw new NotFoundException("Order with id "+ orders.getId() +" not present. So update cannot be performed");
        }
        return orderRepository.save(orders);
    }

    public void deleteOrdersById(Long id){
        Optional<Orders> data = orderRepository.findById(id);
        if(data.isEmpty()){
            throw new NotFoundException("Order with id " + id + " not present. So delete cannot be performed");
        }
        orderRepository.deleteById(id);;
    }

}
