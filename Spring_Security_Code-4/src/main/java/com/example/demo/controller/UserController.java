package com.example.demo.controller;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.CustomAnnotation;
import com.example.demo.entity.User;
import com.example.demo.entity.UserLogin;
import com.example.demo.repository.UserRegister;
import com.example.demo.response.ResponseHandler;
import com.example.demo.response.UserAlreadyExist;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRegister userRegister;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<Object> saveUser(@Valid  @RequestBody User user)
	{
		Optional<User> optional=userRegister.findByEmail(user.getEmail());
		if(optional.isPresent())
		{
			return UserAlreadyExist.responseHandler("Failed", HttpStatus.FOUND, "User Already Exist Login With your Email");
		}
		return ResponseHandler.responseBuilder("Success", HttpStatus.OK, userService.saveUser(user));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<Object> userLogin(@RequestBody UserLogin userLogin)
	{
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return ResponseHandler.responseBuilder("Success", HttpStatus.OK, userLogin);
	}
	
	@PutMapping("/forgetpassword/{email}")
	public ResponseEntity<Object> updatePassword(@RequestBody User user,@PathVariable String email)
	{
		Optional<User> optional=userRegister.findByEmail(email);
		if(optional.isEmpty())
		{
			return UserAlreadyExist.responseHandler("Failed", HttpStatus.NOT_FOUND,"User Doesn't Exist In Database");
		}
		return ResponseHandler.responseBuilder("Success",HttpStatus.OK,userService.updatePassword(user, email));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable  int id)
	{
		Optional<User> optional=userRegister.findById(id);
		if(optional.isEmpty())
		{
			return UserAlreadyExist.responseHandler("Failed",HttpStatus.NOT_FOUND, "User Doesn't Exist In Database");
		}
		userService.deleteEmployee(id);
		return UserAlreadyExist.responseHandler("Success", HttpStatus.OK, "User is Deleted From Database");
	}
}