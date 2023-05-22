package com.onlinestore.demo.controller;

import com.onlinestore.demo.exception.UserNotFoundException;
import com.onlinestore.demo.model.ErrorResponse;
import com.onlinestore.demo.model.User;
import com.onlinestore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

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
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex)
    {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }
}
