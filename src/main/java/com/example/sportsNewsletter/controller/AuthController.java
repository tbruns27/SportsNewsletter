package com.example.sportsNewsletter.controller;

import com.example.sportsNewsletter.model.User;
import com.example.sportsNewsletter.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register"; 
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        userService.registerUser(user);
        model.addAttribute("message", "User registered successfully!");
        return "success"; 
    }
}
