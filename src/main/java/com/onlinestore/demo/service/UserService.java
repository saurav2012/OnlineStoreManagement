package com.onlinestore.demo.service;

import com.onlinestore.demo.exception.UserNotFoundException;
import com.onlinestore.demo.model.User;
import com.onlinestore.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else
           throw new UserNotFoundException("User with id "+ id+" not present");
    }
    public User update(User user){
        Optional<User> data = userRepository.findById(user.getId());
        if(data.isEmpty()){
            throw new UserNotFoundException("User with id "+ user.getId() +" not present. So update cannot be performed");
        }
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        Optional<User> data = userRepository.findById(id);
        if(data.isEmpty()){
            throw new UserNotFoundException("User with id "+ id+" not present. So update cannot be performed");
        }
        userRepository.deleteById(id);;
    }

}
