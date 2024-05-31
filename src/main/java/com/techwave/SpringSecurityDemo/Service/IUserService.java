package com.techwave.SpringSecurityDemo.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.techwave.SpringSecurityDemo.Entity.User;

public interface IUserService {
	public ResponseEntity<User> addUser(User user);
	public ResponseEntity<List<User>> getAllUsers();
		
	

}