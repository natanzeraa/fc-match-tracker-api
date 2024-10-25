package com.scoreboard.scoreboard.services;


import com.scoreboard.scoreboard.models.Player;
import com.scoreboard.scoreboard.dtos.PlayerDTO;
import com.scoreboard.scoreboard.repositories.PlayerRepository;

import com.scoreboard.scoreboard.utils.MergeableDataHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;


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
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No played matches found"));
    }

    public Player create(PlayerDTO playerDTO) {
        var playerModel = new Player();
        BeanUtils.copyProperties(playerDTO, playerModel);
        return playerRepository.save(playerModel);
    }

    public Object update(UUID id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Player not found"));

        player.setId(player.getId());
        player.setName(playerDTO.getName());
        player.setEmail(playerDTO.getEmail());
        player.setCreatedOn(player.getCreatedOn());
        player.setLastUpdate(new Date().toInstant());

        return playerRepository.save(player);
    }

    public Object patch(UUID id, Map<String, Object> parcialData) {
        Player playerModel = playerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No played matches found"));
        mergeData(parcialData, playerModel);
        playerModel = playerRepository.save(playerModel);
        return playerModel;
    }

    public void delete(UUID id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No played matches found"));
        playerRepository.deleteById(player.getId());
    }

}
