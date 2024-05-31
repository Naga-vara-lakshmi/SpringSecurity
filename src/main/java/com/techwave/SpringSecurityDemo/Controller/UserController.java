package com.techwave.SpringSecurityDemo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techwave.SpringSecurityDemo.Entity.User;
import com.techwave.SpringSecurityDemo.Repository.UserRepository;
import com.techwave.SpringSecurityDemo.Service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		return userService.getAllUsers();
	}
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

}
