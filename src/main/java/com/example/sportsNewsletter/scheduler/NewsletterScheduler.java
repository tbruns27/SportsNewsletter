package com.example.sportsNewsletter.scheduler;

import com.example.sportsNewsletter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsletterScheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 600000) 
    public void sendNewsletter() {
        String to = "tbruns027@gmail.com";
        String subject = "Daily Sports Newsletter";
        String body = "Here are today's top sports headlines!";

        emailService.sendEmail(to, subject, body);
        System.out.println("Newsletter sent to: " + to);
    }
}
