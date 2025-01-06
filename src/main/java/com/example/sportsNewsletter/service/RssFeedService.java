package com.example.sportsNewsletter.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
public class RssFeedService {

    public List<String> fetchHeadlines(String rssUrl) {
        List<String> headlines = new ArrayList<>();
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) new URL(rssUrl).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.connect();

            int statusCode = conn.getResponseCode();
            if (isRedirect(statusCode)) {
                String newUrl = conn.getHeaderField("Location");
                conn.disconnect();

                conn = (HttpURLConnection) new URL(newUrl).openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                conn.connect();
                statusCode = conn.getResponseCode();
            }

            if (statusCode == HttpURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (!line.contains("<!DOCTYPE")) {
                            sb.append(line).append("\n");
                        }
                    }
                }

                SyndFeedInput input = new SyndFeedInput();
                try (StringReader sr = new StringReader(sb.toString())) {
                    SyndFeed feed = input.build(sr);
                    for (SyndEntry entry : feed.getEntries()) {
                        headlines.add(entry.getTitle());
                    }
                }
            } else {
                System.err.println("Non-OK HTTP response code: " + statusCode);
            }
        } catch (Exception e) {
            System.err.println("Error fetching/parsing RSS: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return headlines;
    }

    public String getSportUrl(String sport)
    {
        switch (sport)
        {
            case "NBA":
                return "https://www.espn.com/espn/rss/nba/news";
            case "NFL":
                return "https://www.espn.com/espn/rss/nfl/news";
            case "MLB":
                return "https://www.espn.com/espn/rss/mlb/news";
            default:
                throw new IllegalArgumentException("Unknown sport: " + sport);
        }
    }

    public List<String> fetchSportSpecificHeadline(String sport)
    {
        String url = getSportUrl(sport);
        return fetchHeadlines(url);
    }

    private boolean isRedirect(int statusCode) {
        return statusCode == HttpURLConnection.HTTP_MOVED_PERM
            || statusCode == HttpURLConnection.HTTP_MOVED_TEMP
            || statusCode == HttpURLConnection.HTTP_SEE_OTHER;
    }
}
