package com.example.sportsNewsletter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SportsNewsletterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsNewsletterApplication.class, args);
	}

}
