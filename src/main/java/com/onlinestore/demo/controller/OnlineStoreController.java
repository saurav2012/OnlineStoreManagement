package com.onlinestore.demo.controller;

import com.onlinestore.demo.exception.AlreadyPresentException;
import com.onlinestore.demo.exception.NotFoundException;
import com.onlinestore.demo.model.ErrorResponse;
import com.onlinestore.demo.model.Product;
import com.onlinestore.demo.model.User;
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

    @PostMapping("/user/save")
    public User addUser(@RequestBody User user){
        return userService.save(user);
    }
    @GetMapping("/user")
    public List<User> getAllUser(){
        return userService.getAll();
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/user/delete/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }
    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user){
        return userService.update(user);
    }

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

    // handles exceptions ...
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotFoundException(NotFoundException ex)
    {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }
    @ExceptionHandler(value = AlreadyPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAlreadyPresentException(AlreadyPresentException ex)
    {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
    }
}
