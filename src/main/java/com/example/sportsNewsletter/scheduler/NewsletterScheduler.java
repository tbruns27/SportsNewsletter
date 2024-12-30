package com.example.sportsNewsletter.scheduler;


import com.example.sportsNewsletter.service.RssFeedService;
import com.example.sportsNewsletter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsletterScheduler {

    @Autowired
    private RssFeedService rssFeedService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * * * *") 
    public void sendDailyNewsletter() {
        String rssUrl = "http://www.espn.com/espn/rss/news";
        List<String> headlines = rssFeedService.fetchHeadlines(rssUrl);

        StringBuilder emailContent = new StringBuilder("Today's Top Sports Headlines:\n\n");
        for (String headline : headlines) {
            emailContent.append("- ").append(headline).append("\n");
        }
        emailService.sendEmail("tbruns027@gmail.com", "Daily Sports Headlines", emailContent.toString());
    }
}