package com.example.sportsNewsletter.controller;

import com.example.sportsNewsletter.service.RssFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rss")
public class RssFeedController {

    @Autowired
    private RssFeedService rssFeedService;
     
    @GetMapping("/headlines")
    public List<String> getHeadlines(@RequestParam String rssUrl) {
        return rssFeedService.fetchHeadlines(rssUrl);
    }
}
