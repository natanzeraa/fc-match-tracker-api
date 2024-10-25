package com.scoreboard.scoreboard.services;


import com.scoreboard.scoreboard.models.Match;
import com.scoreboard.scoreboard.models.Player;
import com.scoreboard.scoreboard.models.PlayerMatch;
import com.scoreboard.scoreboard.dtos.PlayerMatchDTO;
import com.scoreboard.scoreboard.repositories.MatchRepository;
import com.scoreboard.scoreboard.repositories.PlayerRepository;
import com.scoreboard.scoreboard.repositories.PlayerMatchRepository;

import com.scoreboard.scoreboard.utils.MergeableDataHandler;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.List;


@Service
public class PlayerMatchService extends MergeableDataHandler {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final PlayerMatchRepository playerMatchRepository;

    public PlayerMatchService(
            PlayerMatchRepository playerMatchRepository,
            PlayerRepository playerRepository,
            MatchRepository matchRepository)
    {
        this.playerMatchRepository = playerMatchRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    private PlayerMatch getAndSavePlayerMatchObject(PlayerMatchDTO playerMatchDTO, PlayerMatch playerMatchModel) {

        Player homePlayer = playerRepository.findById(playerMatchDTO.getHomePlayer().getId())
                .orElseThrow(() -> new RuntimeException("Home player not found"));

        Player awayPlayer = playerRepository.findById(playerMatchDTO.getAwayPlayer().getId())
                .orElseThrow(() -> new RuntimeException("Away player not found"));

        Match match = matchRepository.findById(playerMatchDTO.getMatch().getId())
                .orElseThrow(() -> new RuntimeException("Match not found"));

        playerMatchModel.setMatch(match);
        playerMatchModel.setAwayPlayer(awayPlayer);
        playerMatchModel.setHomePlayer(homePlayer);
        playerMatchModel.setHomePlayerGoals(playerMatchDTO.getHomePlayerGoals());
        playerMatchModel.setAwayPlayerGoals(playerMatchDTO.getAwayPlayerGoals());

        return playerMatchRepository.save(playerMatchModel);
    }

    public List<PlayerMatch> findAll() {
        return playerMatchRepository.findAll();
    }

    public PlayerMatch findById(UUID id) {
       return playerMatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No played matches found"));
    }

    public PlayerMatch create(PlayerMatchDTO playerMatchDTO) {
        PlayerMatch playerMatchModel = new PlayerMatch();
        return getAndSavePlayerMatchObject(playerMatchDTO, playerMatchModel);
    }

    public Object update(UUID id, PlayerMatchDTO playerMatchDTO) {
        PlayerMatch playerMatchModel = playerMatchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No played matches found"));
        return getAndSavePlayerMatchObject(playerMatchDTO, playerMatchModel);
    }

    public Object patch(UUID id, Map<String, Object> parcialData) {
        PlayerMatch playerMatchModel = playerMatchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No played matches found"));
        mergeData(parcialData, playerMatchModel);
        playerMatchModel = playerMatchRepository.save(playerMatchModel);
        return playerMatchModel;
    }

    public void delete(UUID id) {
        PlayerMatch playerMatch = playerMatchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No played matches found"));
        playerMatchRepository.deleteById(playerMatch.getId());
    }

}
