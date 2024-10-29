package com.scoreboard.scoreboard.services;


import com.scoreboard.scoreboard.dtos.MatchDTO;
import com.scoreboard.scoreboard.models.Match;
import com.scoreboard.scoreboard.models.Player;
import com.scoreboard.scoreboard.repositories.MatchRepository;
import com.scoreboard.scoreboard.repositories.PlayerRepository;
import com.scoreboard.scoreboard.utils.MergeableDataHandler;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.List;


@Service
public class MatchService extends MergeableDataHandler {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    public MatchService(
            PlayerRepository playerRepository,
            MatchRepository matchRepository)
    {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    private Match getAndSavePlayerMatchObject(MatchDTO matchDTO, Match matchModel) {

        Player homePlayer = playerRepository.findById(matchDTO.getHomePlayer().getId())
                .orElseThrow(() -> new RuntimeException("Home player not found"));

        Player awayPlayer = playerRepository.findById(matchDTO.getAwayPlayer().getId())
                .orElseThrow(() -> new RuntimeException("Away player not found"));


        matchModel.setAwayPlayer(awayPlayer);
        matchModel.setHomePlayer(homePlayer);
        matchModel.setHomePlayerGoals(matchDTO.getHomePlayerGoals());
        matchModel.setAwayPlayerGoals(matchDTO.getAwayPlayerGoals());

        return matchRepository.save(matchModel);
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public Match findById(UUID id) {
       return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No played matches found"));
    }

    public Match create(MatchDTO playerMatchDTO) {
    	Match playerMatchModel = new Match();
        return getAndSavePlayerMatchObject(playerMatchDTO, playerMatchModel);
    }

    public Object update(UUID id, MatchDTO playerMatchDTO) {
    	Match playerMatchModel = matchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No played matches found"));
        return getAndSavePlayerMatchObject(playerMatchDTO, playerMatchModel);
    }

    public Object patch(UUID id, Map<String, Object> parcialData) {
    	Match playerMatchModel = matchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No played matches found"));
        mergeData(parcialData, playerMatchModel);
        playerMatchModel = matchRepository.save(playerMatchModel);
        return playerMatchModel;
    }

    public void delete(UUID id) {
    	Match playerMatch = matchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No played matches found"));
        matchRepository.deleteById(playerMatch.getId());
    }

}
