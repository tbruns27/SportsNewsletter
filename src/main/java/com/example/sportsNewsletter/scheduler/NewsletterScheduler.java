package com.example.sportsNewsletter.scheduler;

import com.example.sportsNewsletter.model.User;
import com.example.sportsNewsletter.service.EmailService;
import com.example.sportsNewsletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsletterScheduler {

    @Autowired
    private UserService userService; 

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * * * *") 
    public void sendNewsletter() {
        List<User> subscribers = userService.getAllSubscribers(); 
        for (User subscriber : subscribers) {
            emailService.sendEmail(
                subscriber.getEmail(),
                "Daily Sports Newsletter",
                "Here are today's top sports headlines!"
            );
            System.out.println("Newsletter sent to: " + subscriber.getEmail());
        }
    }
}
