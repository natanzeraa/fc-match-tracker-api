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

import com.scoreboard.scoreboard.dtos.MatchDTO;
import com.scoreboard.scoreboard.models.Match;
import com.scoreboard.scoreboard.services.MatchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MatchController {

	final private MatchService matchService;

	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@GetMapping
	public ResponseEntity<List<Match>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(matchService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Match> findBYId(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(matchService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Match> create(@RequestBody @Valid MatchDTO matchDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(matchService.create(matchDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid MatchDTO matchDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(matchService.update(id, matchDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> patch(@PathVariable UUID id, @RequestBody @Valid Map<String, Object> partialData) {
		return ResponseEntity.status(HttpStatus.CREATED).body(matchService.patch(id, partialData));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id) {
		matchService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
