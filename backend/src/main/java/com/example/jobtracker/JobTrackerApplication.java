package com.example.jobtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

// UserDetailsServiceAutoConfiguration is excluded because this app is JWT-only:
// nothing uses Spring Security's UserDetailsService, so leaving it enabled just
// creates an in-memory default user and logs a generated password on every boot.
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class JobTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobTrackerApplication.class, args);
	}

}
