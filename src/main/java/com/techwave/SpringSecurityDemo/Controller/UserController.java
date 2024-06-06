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
import com.techwave.SpringSecurityDemo.Service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User",description = "User management APIs")
public class UserController {
	@Autowired
	IUserService userService;
	@Operation(
			summary = "Retrieve Users",
			description = "Get list of Users.The response is list of users with id,username,email,roles,password",
			security =@SecurityRequirement(name = "basicAuth")
			)
	
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status OK",
				content = {@Content(schema = @Schema(implementation =User.class ),mediaType = "application/json")}
						)
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		return userService.getAllUsers();
	}
	@Operation(summary = "Add User to the databse",
			description = "It will take a Json object with username,email,password",
			security = @SecurityRequirement(name = "basicAuth"))
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "OK",content ={@Content(schema = @Schema(implementation =User.class ),mediaType = "application/json")} ),
		@ApiResponse(responseCode = "409",description = "Conflict" ,content= {@Content(schema = @Schema())})
		
	})
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

}
