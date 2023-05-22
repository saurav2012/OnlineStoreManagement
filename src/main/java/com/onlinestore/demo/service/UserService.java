package com.onlinestore.demo.service;

import com.onlinestore.demo.exception.AlreadyPresentException;
import com.onlinestore.demo.exception.NotFoundException;
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
        Optional<User> presentUser = userRepository.findByUsername(user.getUsername());
        if(presentUser.isPresent()){
            throw new AlreadyPresentException(user.getUsername() + " is already present with id " + presentUser.get().getId());
        }
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
           throw new NotFoundException("User with id "+ id+" not present");
    }
    public User update(User user){
        Optional<User> data = userRepository.findById(user.getId());
        if(data.isEmpty()){
            throw new NotFoundException("User with id "+ user.getId() +" not present. So update cannot be performed");
        }
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        Optional<User> data = userRepository.findById(id);
        if(data.isEmpty()){
            throw new NotFoundException("User with id "+ id+" not present. So update cannot be performed");
        }
        userRepository.deleteById(id);;
    }

}
