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
        String confirmationUrl = "http://104.197.224.247:8080/auth/confirm?token=" + token;

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

    public void simulateEmail(String to) {
        // Simulate email sending by logging to the console
        System.out.println("Simulating email to: " + to);
        System.out.println("Subject: Welcome to Skillpath Pro");
        System.out.println("Body: Thank you for signing up! Your account has been successfully created.");
    }
}