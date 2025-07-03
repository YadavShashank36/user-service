package com.example.user_service.service;

import com.example.user_service.dao.UserRepository;
import com.example.user_service.model.User;
import com.example.user_service.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;
    
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("this is email "+email);
        Optional<User> user= repo.findByEmail(email);


        System.out.println(user.get());
        return new  UserPrincipal(user.get());
    }
}
