package com.example.user_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
}
