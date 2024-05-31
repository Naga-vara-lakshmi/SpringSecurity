package com.techwave.SpringSecurityDemo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techwave.SpringSecurityDemo.Entity.User;
import com.techwave.SpringSecurityDemo.Repository.UserRepository;
import com.techwave.SpringSecurityDemo.Service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UserRepository userRepository;
	@Override
	public ResponseEntity<User> addUser(User user) {
		
		
		return ResponseEntity.ok(userRepository.save(user));
	}

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		
		return ResponseEntity.ok(userRepository.findAll());
	}

}
