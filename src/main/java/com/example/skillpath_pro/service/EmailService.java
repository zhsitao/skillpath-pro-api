package com.example.skillpath_pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String to, String token) {
        String confirmationUrl = "http://localhost:8080/auth/confirm?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Confirm your email");
        message.setText("Click the link to confirm your email: " + confirmationUrl);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            // Log the error
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}