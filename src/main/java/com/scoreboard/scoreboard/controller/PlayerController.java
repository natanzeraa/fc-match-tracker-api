package com.scoreboard.scoreboard.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scoreboard.scoreboard.model.PlayerModel;
import com.scoreboard.scoreboard.records.PlayerRecord;
import com.scoreboard.scoreboard.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlayerController {

    PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerModel>> findAllPlayers() {
        return ResponseEntity.status(HttpStatus.OK).body(playerRepository.findAll());
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Object> findPlayerById(@PathVariable(value = "id") long id) {
        Optional<PlayerModel> player = playerRepository.findById(id);

        if (player.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found :(");

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @PostMapping("/player/new")
    public ResponseEntity<PlayerModel> createNewPlayer(@RequestBody @Valid PlayerRecord playerData) {
        var playerModel = new PlayerModel();

        BeanUtils.copyProperties(playerData, playerModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(playerRepository.save(playerModel));
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Object> updatePlayer(@PathVariable(value = "id") long id, @RequestBody @Valid PlayerRecord playerData) {

        Optional<PlayerModel> player = playerRepository.findById(id);

        if (player.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found :(");

        var playerModel = player.get();

        BeanUtils.copyProperties(playerData, playerModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(playerRepository.save(playerModel));
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Object> deletePlayer(@PathVariable(value = "id") long id) {

        Optional<PlayerModel> player = playerRepository.findById(id);

        if (player.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found :(");

        playerRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
