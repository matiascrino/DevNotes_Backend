package com.example.backend_java;

import com.example.backend_java.context.SpringApplicationContext;
import com.example.backend_java.auth.security.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendJavaApplication.class, args);
	}

	@Bean(name ="ApplicationProperties")
	public ApplicationProperties getApplicationProperties() {
	    return new ApplicationProperties();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}


}