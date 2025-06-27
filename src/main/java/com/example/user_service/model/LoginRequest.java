package com.example.user_service.model;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginRequest {
	
	
  private String email;
  private String password;
}
