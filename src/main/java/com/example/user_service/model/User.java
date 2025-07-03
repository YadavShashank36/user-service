package com.example.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	@Column(unique= true,nullable = false)
	private String email;
	@Column( nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	private String address;
	
	public User(String name, String email, String password, String address)
	{
		this.name=name;
		this.email=email;
		this.password=password;
		this.address=address;
		this.role=Role.CUSTOMER;
	}
	public User() {}

	
	
	   
}
