package com.example.sportsNewsletter.scheduler;


import com.example.sportsNewsletter.service.RssFeedService;
import com.example.sportsNewsletter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class NewsletterScheduler {

    @Autowired
    private RssFeedService rssFeedService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * * * *") 
    public void sendDailyNewsletter() {
        List<String> sports = Arrays.asList("NBA", "NFL", "MLB");

        for (String sport : sports) {
            List<String> headlines = rssFeedService.fetchSportSpecificHeadline(sport);

            StringBuilder emailContent = new StringBuilder("Today's Top " + sport + " Headlines:\n\n");
            for (String headline : headlines) {
                emailContent.append("- ").append(headline).append("\n");
            }

            emailService.sendEmail(
                    "tbruns027@gmail.com", 
                    "Daily " + sport + " Headlines", 
                    emailContent.toString()
            );
        }
    }
}