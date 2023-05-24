package com.onlinestore.demo.security;

import com.onlinestore.demo.exception.NotFoundException;
import com.onlinestore.demo.model.Users;
import com.onlinestore.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not found"+username));
        users = new Users(users.getUsername(),users.getPassword(), List.of(new SimpleGrantedAuthority(users.getRole().toString())));
        return users;
    }
}
