package com.techwave.SpringSecurityDemo.IT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techwave.SpringSecurityDemo.Entity.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringSecurityDemoIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getAllUsersTestOk() {
		
	
		ResponseEntity<List<User>> response = restTemplate.withBasicAuth("john@gmail.com", "john@123").exchange("/user/getAllUsers", HttpMethod.GET, null,new ParameterizedTypeReference<List<User>>() {});
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	public void getAllUsersTestForbidden() {
		
	
		ResponseEntity<Object> response = restTemplate.withBasicAuth("mary@gmail.com", "mary@123").exchange("/user/getAllUsers", HttpMethod.GET, null,new ParameterizedTypeReference<Object>() {});
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
//	@Test
//	public void addUserTestOk() {
//		User user=new User();
//		user.setUsername("ram");
//		user.setEmail("ram@gmail.com");
//		user.setPassword("ram@123");
//		HttpEntity<User> userEntity=new HttpEntity<>(user);
//		ResponseEntity<User> response = restTemplate.withBasicAuth("mary@gmail.com", "mary@123").exchange("/user/addUser", HttpMethod.POST,userEntity , User.class);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//	}
	@Test
	public void addUserTestConflict() {
		User user=new User();
		user.setUsername("ram");
		user.setEmail("ram@gmail.com");
		user.setPassword("ram@123");
		HttpEntity<User> userEntity=new HttpEntity<>(user);
		ResponseEntity<String> response = restTemplate.withBasicAuth("mary@gmail.com", "mary@123").exchange("/user/addUser", HttpMethod.POST,userEntity , String.class);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
	@Test
	public void addUserTestForbidden() {
		User user=new User();
		user.setUsername("ram");
		user.setEmail("ram@gmail.com");
		user.setPassword("ram@123");
		HttpEntity<User> userEntity=new HttpEntity<>(user);
		ResponseEntity<Object> response = restTemplate.withBasicAuth("john@gmail.com", "john@123").exchange("/user/addUser", HttpMethod.POST,userEntity , Object.class);
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
	@Test
	public void addUserTestUnauthorized() {
		User user=new User();
		user.setUsername("ram");
		user.setEmail("ram@gmail.com");
		user.setPassword("ram@123");
		HttpEntity<User> userEntity=new HttpEntity<>(user);
		ResponseEntity<Object> response = restTemplate.withBasicAuth("john1@gmail.com", "john@123").exchange("/user/addUser", HttpMethod.POST,userEntity , Object.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

}
