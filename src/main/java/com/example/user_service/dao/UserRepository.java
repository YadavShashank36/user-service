package com.example.user_service.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.user_service.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByEmail(String email);
}
