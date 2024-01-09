package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.annotation.CustomAnnotation;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@CustomAnnotation
	@Override
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updatePassword(User user, String email) {
		User user2=userRepository.findByEmail(email);
		user2.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user2);
	}

	@Override
	public void deleteEmployee(int id) {
		userRepository.findById(id);
		userRepository.deleteById(id);	
	}

	
}
