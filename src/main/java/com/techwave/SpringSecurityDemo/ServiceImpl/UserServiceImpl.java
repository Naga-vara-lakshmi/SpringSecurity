package com.techwave.SpringSecurityDemo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techwave.SpringSecurityDemo.Entity.User;
import com.techwave.SpringSecurityDemo.Repository.UserRepository;
import com.techwave.SpringSecurityDemo.Service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public ResponseEntity<Object> addUser(User user) {
		User userFound = userRepository.findByEmail(user.getEmail());
		if(userFound==null)
		{
			user.setRoles("ROLE_USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		
			return ResponseEntity.ok(userRepository.save(user));
		}
		return ResponseEntity.status(409).body("User with email "+user.getEmail()+" is present");
			

	}

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		
		return ResponseEntity.ok(userRepository.findAll());
	}

	@Override
	public ResponseEntity<Object> getUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id "+id+" is not present");
	}

	

}
