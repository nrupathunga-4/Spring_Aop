package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public User updatePassword(User user,String email);
	
	public void deleteEmployee(int id);

}
