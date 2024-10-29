package com.scoreboard.scoreboard.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scoreboard.scoreboard.dtos.PlayerDTO;
import com.scoreboard.scoreboard.models.Player;
import com.scoreboard.scoreboard.services.PlayerService;

import jakarta.validation.Valid;


@RestController
@RequestMapping( "/players")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlayerController {

    PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Player> create(@RequestBody @Valid PlayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.create(playerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid PlayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.update(id, playerDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patch(@PathVariable UUID id, @RequestBody @Valid Map<String, Object> parcialData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.patch(id, parcialData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        playerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
