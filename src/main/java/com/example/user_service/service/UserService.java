package com.example.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	AuthenticationManager authenticationManager;
     JWTService jwtService;
  private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
	@Autowired
	public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,JWTService jwtService)
	{
		this.userRepository=userRepository;
		this.authenticationManager=authenticationManager;
       this.jwtService=jwtService;
	}
	
	
	public User userLogin(LoginRequest loginRequest)
	{
		Optional<User> user=userRepository.findByEmail(loginRequest.getEmail());
		if(user.isEmpty()) {throw new UserNotFoundException("invalid Credentials"); }
		
		 User us= user.get();
		String s=encoder.encode(us.getPassword());
		us.setPassword(s);
		if(!us.getPassword().equals(loginRequest.getPassword()))
		{throw new UserNotFoundException("invalid Credentials"); }
		
		return us;
		
		
	}
	
	public User addUser(User usert)
	{
		Optional<User> user=userRepository.findByEmail(usert.getEmail());
		if(!user.isEmpty()) {throw new UserNotFoundException("User with this email already exist"); }
//		// usert.setPassword(encoder.encode(usert.getPassword()));


		usert.setPassword(encoder.encode((usert.getPassword())));
		userRepository.save(usert);
		return  usert;
		
	}
	public String getUser(LoginRequest loginRequest)
	{

		Authentication authentication= authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));



		//Optional<User> user=userRepository.findByEmail(loginRequest.getEmail());
		if(!authentication.isAuthenticated()) {throw new UserNotFoundException("invalid Credentials"); }
        return  jwtService.generateToken(loginRequest.getEmail());


	}
	

}
