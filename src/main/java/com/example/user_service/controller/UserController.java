package com.example.user_service.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.user_service.errorhandling.UserNotFoundException;
import com.example.user_service.model.LoginRequest;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;


@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService)
	{
		this.userService=userService;
	}

	@GetMapping("/")
	public int begin()
	{
		return 35;
	}
	@PostMapping("/login")
	public String userLogin(@RequestBody LoginRequest loginRequest)
	{

		String str=userService.getUser(loginRequest);
     System.out.println(str);
	 return  str;


	}

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody User user)
	{
		try {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));

		}
		catch(UserNotFoundException e)
		{
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(e.getMessage());
		}

	}

//	@PostMapping("/profile")
//	public ResponseEntity<?> getUser(@RequestBody LoginRequest loginRequest)
//	{
//		try {
//			User user= userService.getUser(loginRequest);
//			return ResponseEntity.ok(user);
//		}
//		catch(UserNotFoundException e)
//		{return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());}
//
//	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		String productServiceUrl = "http://localhost:8082/products"; // Change port as per your product-service
		Product[] products = restTemplate.getForObject(productServiceUrl, Product[].class);
		return Arrays.asList(products);
	}






}
