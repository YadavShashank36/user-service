package com.example.user_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class LoginRequest {
	
	
  private String email;
  private String password;
  
}
