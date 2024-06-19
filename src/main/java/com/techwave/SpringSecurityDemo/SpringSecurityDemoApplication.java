package com.techwave.SpringSecurityDemo;

import org.springframework.boot.SpringApplication;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringSecurityDemoApplication {

	public static void main(String[] args) throws SQLException {
		Server.createTcpServer().start();
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

}
