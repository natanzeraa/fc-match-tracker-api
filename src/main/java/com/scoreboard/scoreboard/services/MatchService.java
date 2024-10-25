package com.scoreboard.scoreboard.services;


import com.scoreboard.scoreboard.models.Match;
import com.scoreboard.scoreboard.dtos.MatchDTO;
import com.scoreboard.scoreboard.models.Player;
import com.scoreboard.scoreboard.repositories.MatchRepository;

import com.scoreboard.scoreboard.utils.MergeableDataHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService extends MergeableDataHandler {

    MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public Match findById(UUID id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
    }

    public Match create(MatchDTO matchrDTO) {
        var matchModel = new Match();
        BeanUtils.copyProperties(matchrDTO, matchModel);
        return matchRepository.save(matchModel);
    }

    public Object update(UUID id, MatchDTO matchDTO) {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Mach not found"));

        match.setId(match.getId());
        match.setTitle(matchDTO.getTitle());
        match.setCreatedOn(match.getCreatedOn());
        match.setLastUpdate(new Date().toInstant());

        return matchRepository.save(match);
    }

    public Object patch(UUID id, Map<String, Object> parcialData) {
        Match matchModel = matchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Mach not found"));
        mergeData(parcialData, matchModel);
        matchModel = matchRepository.save(matchModel);
        return matchModel;
    }

    public void delete(UUID id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Mach not found"));
        matchRepository.deleteById(match.getId());
    }
}
