package com.example.sportsNewsletter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login"; 
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; 
    }
}
