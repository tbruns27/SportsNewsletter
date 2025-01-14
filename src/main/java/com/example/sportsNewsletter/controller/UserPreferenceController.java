package com.example.sportsNewsletter.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sportsNewsletter.model.Sport;
import com.example.sportsNewsletter.model.User;
import com.example.sportsNewsletter.repository.SportRepository;
import com.example.sportsNewsletter.repository.UserRepository;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportRepository sportRepository;

    @PostMapping("/{userId}")
    public void saveUserSportsPreferences(@PathVariable Long userId, @RequestBody List<Integer> sportIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Set<Sport> selectedSports = new HashSet<>(sportRepository.findAllById(sportIds));
        user.setSports(selectedSports);
        userRepository.save(user);
    }
}
