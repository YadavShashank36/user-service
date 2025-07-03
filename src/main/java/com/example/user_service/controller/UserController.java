package com.example.user_service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.user_service.errorhandling.UserNotFoundException;
import com.example.user_service.model.LoginRequest;
import com.example.user_service.model.Product;
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

	
	@PostMapping("/login")
	public String userLogin(@RequestBody LoginRequest loginRequest)
	{

		String str=userService.getUser(loginRequest);
     System.out.println(str);
	 return  str;


	}

	@PostMapping("/register")
	public ResponseEntity<?> addCustomer(@RequestBody User user) {
	    try {
	           
	        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
	    } catch (UserNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody User user, Authentication auth)
	{
		
		 try {
                 // userService.updatePassword(user, auth);		
			         userService.updatePassword(user, auth);
		        return ResponseEntity.status(HttpStatus.CREATED).body("Password changed");
		    } catch (UserNotFoundException e) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		    }
    


	}
	
	@PutMapping("/login/forgotPassword")
	public ResponseEntity<?> resetPassword(@RequestBody User user)
	{
		try {
			 userService.resetPassword(user);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Password changed");
	    } catch (UserNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<?> updateUser(@RequestBody User user, Authentication auth)
	{
		try {
			    User usert=userService.updateUser(user, auth);
	        return ResponseEntity.status(HttpStatus.CREATED).body(usert);
	    } catch (UserNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
     @GetMapping("/customers")
     public double check(Authentication auth)
     {
    	 if(!auth.isAuthenticated())
 		{
 			System.out.println("NOt Validated");
 		}
    	 return 5;
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

//	@GetMapping("/products")
//	public List<Product> getAllProducts() {
//		String productServiceUrl = "http://localhost:8082/products"; // Change port as per your product-service
//		Product[] products = restTemplate.getForObject(productServiceUrl, Product[].class);
//		return Arrays.asList(products);
//	}






}
