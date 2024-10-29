package com.scoreboard.scoreboard.controllers;

import com.scoreboard.scoreboard.models.Player;
import com.scoreboard.scoreboard.dtos.PlayerDTO;
import com.scoreboard.scoreboard.services.PlayerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.UUID;
import java.util.List;


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
    public ResponseEntity<Player> create(@RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.create(playerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.update(id, playerDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patch(@PathVariable UUID id, @RequestBody Map<String, Object> parcialData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.patch(id, parcialData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        playerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
