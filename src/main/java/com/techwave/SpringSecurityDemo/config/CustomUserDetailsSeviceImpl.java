package com.techwave.SpringSecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techwave.SpringSecurityDemo.Entity.User;
import com.techwave.SpringSecurityDemo.Repository.UserRepository;
@Service
public class CustomUserDetailsSeviceImpl implements UserDetailsService {
		@Autowired
		UserRepository userRepository;
	
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User userFound = userRepository.findByEmail(username);
			if(userFound==null) throw new UsernameNotFoundException("User with email "+username+" is not found");
			return new CustomUserDetails(userFound);
					
			
		}
		

}
