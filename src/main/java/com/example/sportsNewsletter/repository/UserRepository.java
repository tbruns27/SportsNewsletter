package com.example.sportsNewsletter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sportsNewsletter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

