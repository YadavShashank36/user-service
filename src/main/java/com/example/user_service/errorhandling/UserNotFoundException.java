package com.example.user_service.errorhandling;



import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {
    private final HttpStatus status;
    
    public UserNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.UNAUTHORIZED; // Default to 401 Unauthorized
    }
    
    public UserNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
