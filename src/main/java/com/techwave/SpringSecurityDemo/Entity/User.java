package com.techwave.SpringSecurityDemo.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserTable")
@Schema(name = "User",description = "Schema to hold user details")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(name = "id",requiredMode =  RequiredMode.NOT_REQUIRED,example = "1")
	private int id;
	@Schema(name="user_name",description = "name of the user",example="john",requiredMode = RequiredMode.REQUIRED)
	private String username;
	@Schema(description = "email of the user",example="john@gmail.com",requiredMode = RequiredMode.REQUIRED)
	private String email;
	@Schema(description = "password",example="12367",requiredMode = RequiredMode.REQUIRED)
	private String password;
	@Schema(requiredMode = RequiredMode.NOT_REQUIRED,example = "ROLE_USER")
	private String roles;
	public User() {
		
	}
	
	public User(int id, String username, String email, String password, String roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}