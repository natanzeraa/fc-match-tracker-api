package com.scoreboard.scoreboard.controllers;

import com.scoreboard.scoreboard.models.PlayerMatch;
import com.scoreboard.scoreboard.dtos.PlayerMatchDTO;
import com.scoreboard.scoreboard.services.PlayerMatchService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/played-match")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlayerMatchController {

    PlayerMatchService playerMatchService;

    public PlayerMatchController(PlayerMatchService playerMatchService) {
        this.playerMatchService = playerMatchService;
    }


    @GetMapping
    public ResponseEntity<List<PlayerMatch>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(playerMatchService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(playerMatchService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PlayerMatch> create(@RequestBody @Valid PlayerMatchDTO playerMatchDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerMatchService.create(playerMatchDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody PlayerMatchDTO playerMatchDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerMatchService.update(id, playerMatchDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody Map<String, Object> parcialData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerMatchService.patch(id, parcialData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        playerMatchService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
