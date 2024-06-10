package com.techwave.SpringSecurityDemo.Controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techwave.SpringSecurityDemo.Entity.User;
import com.techwave.SpringSecurityDemo.Service.IUserService;
import com.techwave.SpringSecurityDemo.config.CustomUserDetailsSeviceImpl;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IUserService useService;
	@Test
	public void getAllUsersTestOK() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders.get("/getAllUsers").accept(MediaType.APPLICATION_JSON);
		List<User> actual=Arrays.asList(new User(1,"mary","mary@gmail.ocm","$2a$10$qSrM4Qgs5BHg/H5scZ6zleigHEjxP4FGjBsC/qAZbUW3CPc/S/P9i","ROLE_ADMIN"),
				new User(2,"john","john@gmail.ocm","$2a$10$wwvRds77tKxwgcyfw.UYKOd3PaCg8geueMpzZc07/7UjNNd0z1Lkq","ROLE_USER")
		        );
		when(useService.getAllUsers()).thenReturn(ResponseEntity.ok(actual));
		ResultActions result = mockMvc.perform(get("/user/getAllUsers").with(SecurityMockMvcRequestPostProcessors.user("john@gmail.com"))).andExpect(status().isOk());
		assertThat(actual,hasSize(2));
	}
	
	
	@Test
	public void getAllUsersTestForbidden() throws Exception {

		when(useService.getAllUsers()).thenReturn(ResponseEntity.status(403).build());
		ResultActions result = mockMvc.perform(get("/user/getAllUsers").with(SecurityMockMvcRequestPostProcessors.user("mary@gmail.com").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))).andExpect(status().isForbidden());
		
	}
	
	@Test
	public void addUsersTestOK() throws Exception {
		ObjectMapper userJson=new ObjectMapper();
		User user=new User();
		user.setEmail("smith@gmail.com");
		user.setPassword("smith@123");
		user.setUsername("smith");
		String json = userJson.writeValueAsString(user);
		
		when(useService.addUser(user)).thenReturn(ResponseEntity.ok(new User(3,"smith","smith@gmail.ocm","$2a$10$wwvRds77tKxwgcyfw.UYKOd3PaCg8geueMpzZc07/7UjNNd0z1Lkq","ROLE_USER")));
		mockMvc.perform(post("/user/addUser").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON).with(SecurityMockMvcRequestPostProcessors.user("mary@gmail.com").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))).andExpect(status().isOk());
		
	}
	@Test
	public void addUsersTestConflict() throws Exception {
		ObjectMapper userJson=new ObjectMapper();
		User user=new User();
		user.setEmail("smith@gmail.com");
		user.setPassword("smith@123");
		user.setUsername("smith");
		String json = userJson.writeValueAsString(user);
		
		when(useService.addUser(any(User.class))).thenReturn(ResponseEntity.status(409).build());
		mockMvc.perform(post("/user/addUser").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON).with(SecurityMockMvcRequestPostProcessors.user("mary@gmail.com").authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))).andExpect(status().isConflict());
		
	}
	
	@Test
	public void addUsersTestForbidden() throws Exception {
		ObjectMapper userJson=new ObjectMapper();
		User user=new User();
		user.setEmail("smith@gmail.com");
		user.setPassword("smith@123");
		user.setUsername("smith");
		String json = userJson.writeValueAsString(user);
		
		when(useService.addUser(any(User.class))).thenReturn(ResponseEntity.status(403).build());
		mockMvc.perform(post("/user/addUser").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON).with(SecurityMockMvcRequestPostProcessors.user("mary@gmail.com").authorities(new SimpleGrantedAuthority("ROLE_USER")))).andExpect(status().isForbidden());
		
	}
}
