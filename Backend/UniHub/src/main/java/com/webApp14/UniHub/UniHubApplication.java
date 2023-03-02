package com.webApp14.UniHub;

import com.webApp14.UniHub.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class UniHubApplication {

	@Autowired
	private EmailService emailService;
	public static void main(String[] args) {
		SpringApplication.run(UniHubApplication.class, args);
	}

}
