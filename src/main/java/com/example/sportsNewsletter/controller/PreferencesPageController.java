package com.example.sportsNewsletter.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sportsNewsletter.model.Sport;
import com.example.sportsNewsletter.model.User;
import com.example.sportsNewsletter.repository.SportRepository;
import com.example.sportsNewsletter.repository.UserRepository;

@Controller
public class PreferencesPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportRepository sportRepository;

    @GetMapping("/preferences")
    public String showPreferencesPage(Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
    
        model.addAttribute("user", user);

        List<Sport> sports = sportRepository.findAll();
        model.addAttribute("allSports", sports);
        model.addAttribute("userId", user.getId());
    
        return "preferences"; 
    }
    
}


