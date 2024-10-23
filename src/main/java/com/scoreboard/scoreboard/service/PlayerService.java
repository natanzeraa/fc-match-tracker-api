package com.scoreboard.scoreboard.service;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    // findAll
    public String findAll() {
        return "This finds all the players in database";
    }

    // findById
    public String findById(Long id) {
        return "This one returns the player by its id";
    }
}
