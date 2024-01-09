package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRegister extends JpaRepository<User, Integer>{

	public Optional<User> findByEmail(String email);
}
