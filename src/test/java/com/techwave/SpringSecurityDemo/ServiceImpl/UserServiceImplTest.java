package com.techwave.SpringSecurityDemo.ServiceImpl;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techwave.SpringSecurityDemo.Entity.User;
import com.techwave.SpringSecurityDemo.Repository.UserRepository;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	@InjectMocks
	UserServiceImpl userService;
	@Mock
	UserRepository userRepository;
	@Mock
	PasswordEncoder passwordEncoder;
	
	
	@Test
	public void getAllUsersTestOk() {
		List<User> actual=Arrays.asList(new User(1,"mary","mary@gmail.ocm","$2a$10$qSrM4Qgs5BHg/H5scZ6zleigHEjxP4FGjBsC/qAZbUW3CPc/S/P9i","ROLE_ADMIN"),
				new User(2,"john","john@gmail.ocm","$2a$10$wwvRds77tKxwgcyfw.UYKOd3PaCg8geueMpzZc07/7UjNNd0z1Lkq","ROLE_USER"));
		
		when(userRepository.findAll()).thenReturn(actual);
		ResponseEntity<List<User>> response = userService.getAllUsers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(userRepository,times(1)).findAll();
		assertEquals(actual,response.getBody());
		
		
	}
	@Test
	public void getAllUsersEmptyListOk() {
		when(userRepository.findAll()).thenReturn(Arrays.asList());
		ResponseEntity<List<User>> allUsers = userService.getAllUsers();
		assertEquals(HttpStatus.OK, allUsers.getStatusCode());
		verify(userRepository,times(1)).findAll();
		assertEquals(Arrays.asList(),allUsers.getBody());
	}
	@Test
	public void addUsersTestOk() {
		
		User user=new User();
		user.setEmail("smith@gmail.com");
		user.setPassword("smith@123");
		user.setUsername("smith");
	
		when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		when(passwordEncoder.encode(user.getPassword())).thenReturn("$2a$10$wwvRds77tKxwgcyfw.UYKOd3PaCg8geueMpzZc07/7UjNNd0z1Lkq");
		when(userRepository.save(any(User.class))).thenReturn(user);
		ResponseEntity<Object> response = userService.addUser(user);
		System.out.println(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	public void addUsersTestConflict() {
		
		User user=new User();
		user.setEmail("smith@gmail.com");
		user.setPassword("smith@123");
		user.setUsername("smith");
	
		when(userRepository.findByEmail(user.getEmail())).thenReturn(new User(3,"smith","smith@gmail.com","$2a$10$wwvRds77tKxwgcyfw.UYKOd3PaCg8geueMpzZc07/7UjNNd0z1Lkq","ROLE_USER"));
		
		ResponseEntity<Object> response = userService.addUser(user);
		System.out.println(response.getBody());
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
	

	

	

}
