package com.scoreboard.scoreboard.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.scoreboard.scoreboard.dtos.PlayerDTO;
import com.scoreboard.scoreboard.exceptions.NotFoundException;
import com.scoreboard.scoreboard.models.Player;
import com.scoreboard.scoreboard.repositories.PlayerRepository;
import com.scoreboard.scoreboard.utils.MergeableDataHandler;

@Service
public class PlayerService extends MergeableDataHandler {

	private final PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	public Player findById(UUID id) {
		return playerRepository.findById(id).orElseThrow(() -> new NotFoundException("Player not found"));
	}

	public Player create(PlayerDTO playerDTO) {
		if (playerRepository.existsByEmail(playerDTO.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}

		var newPlayer = new Player();
		BeanUtils.copyProperties(playerDTO, newPlayer);
		return playerRepository.save(newPlayer);
	}

	public Object update(UUID id, PlayerDTO playerDTO) {
		Player player = playerRepository.findById(id).orElseThrow(null);

		player.setId(player.getId());
		player.setName(playerDTO.getName());
		player.setEmail(playerDTO.getEmail());
		player.setCreatedOn(player.getCreatedOn());
		player.setLastUpdate(new Date().toInstant());

		return playerRepository.save(player);
	}

	public Object patch(UUID id, Map<String, Object> parcialData) {
		var playerModel = playerRepository.findById(id).orElseThrow(null);
		mergeData(parcialData, playerModel);
		playerModel = playerRepository.save(playerModel);
		return playerModel;
	}

	public void delete(UUID id) {
		Player player = playerRepository.findById(id).orElseThrow(() -> new NotFoundException("Player not found"));
		playerRepository.deleteById(player.getId());
	}

}
