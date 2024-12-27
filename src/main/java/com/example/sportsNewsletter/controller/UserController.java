package com.example.sportsNewsletter.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.sportsNewsletter.model.Sport;
import com.example.sportsNewsletter.model.User;
import com.example.sportsNewsletter.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Add sports to a user
    @PostMapping("/{userId}/sports")
    public User addSportsToUser(@PathVariable Long userId, @RequestBody Set<Integer> sportIds) {
        return userService.addSportsToUser(userId, sportIds);
    }

    // Get all sports for a user
    @GetMapping("/{userId}/sports")
    public Set<Sport> getUserSports(@PathVariable Long userId) {
        return userService.getUserSports(userId);
    }
}
