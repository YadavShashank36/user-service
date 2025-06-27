package com.example.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.user_service.dao.UserRepository;
import com.example.user_service.errorhandling.UserNotFoundException;
import com.example.user_service.model.LoginRequest;
import com.example.user_service.model.User;

import lombok.Data;

@Service

public class UserService {
	
	UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}
	
	
	public User userLogin(LoginRequest loginRequest)
	{
		Optional<User> user=userRepository.findByEmail(loginRequest.getEmail());
		if(user.isEmpty()) {throw new UserNotFoundException("invalid Credentials"); }
		
		 User us= user.get();
		if(!us.getPassword().equals(loginRequest.getPassword()))
		{throw new UserNotFoundException("invalid Credentials"); }
		
		return us;
		
		
	}
	
	public User addUser(User usert)
	{
		Optional<User> user=userRepository.findByEmail(usert.getEmail());
		if(!user.isEmpty()) {throw new UserNotFoundException("User with this email already exist"); }
		
		userRepository.save(usert);
		return usert;
		
	}
	public User getUser(LoginRequest loginRequest)
	{
		Optional<User> user=userRepository.findByEmail(loginRequest.getEmail());
		if(user.isEmpty()) {throw new UserNotFoundException("invalid Credentials"); }
		
		 User us= user.get();
		if(!us.getPassword().equals(loginRequest.getPassword())) 
		{ throw new UserNotFoundException("invalid Credentials");}
		return us;
	}
	

}
