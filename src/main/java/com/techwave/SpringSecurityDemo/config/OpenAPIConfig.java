package com.techwave.SpringSecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(name = "basicAuthentication",type = SecuritySchemeType.HTTP,scheme ="basic" )
public class OpenAPIConfig {
	@Bean
	public OpenAPI openApi() {
		Contact contact=new Contact()
				.name("lakshmi")
				.email("lakshmi@gmail.com")
				.url("https://www.lakshmi.com");
		Info info=new Info()
				.title("User Management API")
				.version("1.0")
				.description("This API exposes endpoints to manage users")
				.contact(contact);
		return new OpenAPI().info(info);

	}

}
