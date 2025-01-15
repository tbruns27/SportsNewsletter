package com.example.sportsNewsletter.scheduler;
import com.example.sportsNewsletter.service.NewsletterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsletterScheduler {

    @Autowired
    NewsletterService newsletterService;

    @Scheduled(cron = "0 0 * * * *") 
    public void sendDailyNewsletter() {
        newsletterService.sendDailyNewsletterInsideTransaction();
    }

}