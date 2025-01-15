package com.example.sportsNewsletter.service;

import com.example.sportsNewsletter.model.Sport;
import com.example.sportsNewsletter.model.User;
import com.example.sportsNewsletter.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class NewsletterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RssFeedService rssFeedService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void sendDailyNewsletterInsideTransaction() {
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            Set<Sport> sports = user.getSports();
            if (sports == null || sports.isEmpty()) {
                continue;
            }

            StringBuilder emailContent = new StringBuilder();
            emailContent.append("Here's your daily sports newsletter:\n\n");

            for (Sport sport : sports) {
                List<String> headlines = rssFeedService.fetchSportSpecificHeadline(sport.getName());
                
                emailContent.append("** ").append(sport.getName()).append(" Headlines **\n");
                for (String headline : headlines) {
                    emailContent.append(" - ").append(headline).append("\n");
                }
                emailContent.append("\n");
            }

            emailService.sendEmail(
                    user.getEmail(),
                    "Your Daily Sports Newsletter",
                    emailContent.toString()
            );
        }
    }
}
