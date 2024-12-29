package com.example.sportsNewsletter.service;

import com.example.sportsNewsletter.model.Sport;
import com.example.sportsNewsletter.model.User;
import com.example.sportsNewsletter.repository.SportRepository;
import com.example.sportsNewsletter.repository.UserRepository;
import com.example.sportsNewsletter.security.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportRepository sportRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register a new user
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
        return userRepository.save(user);
    }

    // Retrieve a user by ID
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Find a user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Add sports to a user
    public User addSportsToUser(Long userId, Set<Integer> sportIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Sport> sports = sportRepository.findAllById(sportIds).stream().collect(Collectors.toSet());
        user.setSports(sports);
        return userRepository.save(user);
    }

    // Retrieve all sports for a user
    public Set<Sport> getUserSports(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getSports();
    }

    // Load user by email for Spring Security
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return new CustomUserDetails(user); // Wrap User in CustomUserDetails
    }

    public List<User> getAllSubscribers()
    {
        return userRepository.findAll();
    }
}
