package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name Should Not Be Null")
	@Pattern(regexp = "^[a-zA-Z]+$",message ="name Should Consist Letters")
	private String name;
	private String departmentName;
	@Email(message = "Please Provide A proper Email")
	private String email;
	@NotBlank(message = "Password Should Not Be Null")
	private String password;
	@NotBlank(message = "Please Provide a Proper Role")
	private String role;
	
	public User() {
		super();
		
	}

	public User(int id, String name, String departmentName, String email, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.departmentName = departmentName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", departmentName=" + departmentName + ", email=" + email
				+ ", password=" + password + ", role=" + role + "]";
	}	
}
