package com.onlinestore.demo.service;

import com.onlinestore.demo.exception.AlreadyPresentException;
import com.onlinestore.demo.exception.NotFoundException;
import com.onlinestore.demo.model.Users;
import com.onlinestore.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users save(Users users){
        Optional<Users> presentUser = userRepository.findByUsername(users.getUsername());
        if(presentUser.isPresent()){
            throw new AlreadyPresentException(users.getUsername() + " is already present with id " + presentUser.get().getId());
        }
        return userRepository.save(users);
    }
    public List<Users> getAll(){
        return userRepository.findAll();
    }
    public Users getUserById(Long id){
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else
           throw new NotFoundException("User with id "+ id+" not present");
    }
    public Users update(Users users){
        Optional<Users> data = userRepository.findById(users.getId());
        if(data.isEmpty()){
            throw new NotFoundException("User with id "+ users.getId() +" not present. So update cannot be performed");
        }
        return userRepository.save(users);
    }

    public void deleteUserById(Long id){
        Optional<Users> data = userRepository.findById(id);
        if(data.isEmpty()){
            throw new NotFoundException("User with id "+ id+" not present. So delete cannot be performed");
        }
        userRepository.deleteById(id);;
    }

}
