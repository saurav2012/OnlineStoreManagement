package com.onlinestore.demo.controller;

import com.onlinestore.demo.exception.AlreadyPresentException;
import com.onlinestore.demo.exception.NotFoundException;
import com.onlinestore.demo.model.*;
import com.onlinestore.demo.service.OrderItemService;
import com.onlinestore.demo.service.OrderService;
import com.onlinestore.demo.service.ProductService;
import com.onlinestore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OnlineStoreController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/user/save")
    public Users addUser(@RequestBody Users users){
        return userService.save(users);
    }
    @GetMapping("/user")
    public List<Users> getAllUser(){
        return userService.getAll();
    }
    @GetMapping("/user/{id}")
    public Users getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/user/delete/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }
    @PutMapping("/user/update")
    public Users updateUser(@RequestBody Users users){
        return userService.update(users);
    }

    //products endpoints

    @PostMapping("/product/save")
    public Product addProduct(@RequestBody Product product){
        return productService.save(product);
    }
    @GetMapping("/product")
    public List<Product> getAllProduct(){
        return productService.getAll();
    }
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @DeleteMapping("/product/delete/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }
    @PutMapping("/product/update")
    public Product updateProduct(@RequestBody Product product){
        return productService.update(product);
    }

    // orders endpoints
    @PostMapping("/orders/save")
    public Orders addOrders(@RequestBody Orders orders){
        return orderService.save(orders);
    }
    @GetMapping("/orders")
    public List<Orders> getAllOrders(){
        return orderService.getAll();
    }
    @GetMapping("/orders/{id}")
    public Orders getOrdersById(@PathVariable Long id){
        return orderService.getOrdersById(id);
    }
    @DeleteMapping("/orders/delete/{id}")
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteOrdersById(id);
    }
    @PutMapping("/orders/update")
    public Orders updateOrders(@RequestBody Orders orders){
        return orderService.update(orders);
    }

    // orderItems
    @PostMapping("/orderItems/save")
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.save(orderItem);
    }
    @GetMapping("/orderItems")
    public List<OrderItem> getAllOrderItems(){
        return orderItemService.getAll();
    }
    @GetMapping("/orderItems/{id}")
    public OrderItem getOrderItemsById(@PathVariable Long id){
        return orderItemService.getOrderItemsById(id);
    }
    @DeleteMapping("/orderItems/delete/{id}")
    public void deleteOrdeItemById(@PathVariable Long id){
        orderItemService.deleteOrderItemsById(id);
    }
    @PutMapping("/orderItems/update")
    public OrderItem updateOrderItems (@RequestBody OrderItem orderItem){
        return orderItemService.update(orderItem);
    }


    // handles exceptions ...
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException ex)
    {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }
    @ExceptionHandler(value = AlreadyPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAlreadyPresentException(AlreadyPresentException ex)
    {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
    }
}
