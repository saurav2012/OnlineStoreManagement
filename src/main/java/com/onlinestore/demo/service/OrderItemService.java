package com.onlinestore.demo.service;

import com.onlinestore.demo.exception.AlreadyPresentException;
import com.onlinestore.demo.exception.NotFoundException;
import com.onlinestore.demo.model.OrderItem;
import com.onlinestore.demo.model.Orders;
import com.onlinestore.demo.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    public List<OrderItem> getAll(){
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemsById(Long id){
        Optional<OrderItem> orderItems = orderItemRepository.findById(id);
        if(orderItems.isPresent()){
            return orderItems.get();
        }
        else
            throw new NotFoundException("Order with id "+ id+" not present");
    }

    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    public OrderItem update(OrderItem orderItem){
        Optional<OrderItem> data = orderItemRepository.findById(orderItem.getId());
        if(data.isEmpty()){
            throw new NotFoundException("Order with id "+ orderItem.getId() +" not present. So update cannot be performed");
        }
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItemsById(Long id){
        Optional<OrderItem> data = orderItemRepository.findById(id);
        if(data.isEmpty()){
            throw new NotFoundException("Order with id " + id + " not present. So delete cannot be performed");
        }
        orderItemRepository.deleteById(id);;
    }

}
