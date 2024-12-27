package com.example.sportsNewsletter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sportsNewsletter.model.Sport;

public interface SportRepository extends JpaRepository<Sport, Integer> {
    Sport findByName(String name);
}

