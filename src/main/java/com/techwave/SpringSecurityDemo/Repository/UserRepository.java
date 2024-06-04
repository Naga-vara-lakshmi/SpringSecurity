
package com.techwave.SpringSecurityDemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techwave.SpringSecurityDemo.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);

}