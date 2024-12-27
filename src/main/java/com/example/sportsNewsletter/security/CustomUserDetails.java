package com.example.sportsNewsletter.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.sportsNewsletter.model.User;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // You can add roles/authorities here if needed
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Use email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Set your business logic here if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Set your business logic here if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Set your business logic here if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Set your business logic here if needed
    }

    public User getUser() {
        return user; // Provide access to the actual User entity if needed
    }
}
