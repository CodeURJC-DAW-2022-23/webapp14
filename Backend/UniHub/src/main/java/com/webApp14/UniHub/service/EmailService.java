package com.webApp14.UniHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // Attributes
    @Autowired
    private JavaMailSender mailSender;

    // Constructor
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        System.out.println("MailSender: " + mailSender);
    }

    // Method that sends an email to a designated subject and with a predefined content
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("unihubcontacturjc@gmail.com");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);
        mailSender.send(email);
    }

}
