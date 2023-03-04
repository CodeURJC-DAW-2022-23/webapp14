package com.webApp14.UniHub;

import com.webApp14.UniHub.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniHubApplication {

	@Autowired
	private EmailService emailService;
	public static void main(String[] args) {
		SpringApplication.run(UniHubApplication.class, args);
	}

}
