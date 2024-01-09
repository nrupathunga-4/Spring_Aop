package com.example.demo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserLogin {

	@NotBlank(message = "userName Should Not Be Null")
	@Pattern(regexp = "^[a-zA-Z]+$",message ="name Should Consist Letters")
	private String userName;
	@NotBlank(message = "Password Should Not Be Null")
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
