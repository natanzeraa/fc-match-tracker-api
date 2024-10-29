package com.scoreboard.scoreboard.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.scoreboard.scoreboard.dtos.MatchDTO;
import com.scoreboard.scoreboard.exceptions.NotFoundException;
import com.scoreboard.scoreboard.models.Match;
import com.scoreboard.scoreboard.models.Player;
import com.scoreboard.scoreboard.repositories.MatchRepository;
import com.scoreboard.scoreboard.repositories.PlayerRepository;
import com.scoreboard.scoreboard.utils.MergeableDataHandler;

@Service
public class MatchService extends MergeableDataHandler {

	private final PlayerRepository playerRepository;
	private final MatchRepository matchRepository;

	public MatchService(PlayerRepository playerRepository, MatchRepository matchRepository) {
		this.playerRepository = playerRepository;
		this.matchRepository = matchRepository;
	}

	private Match getAndSavePlayerMatchObject(MatchDTO matchDTO, Match matchModel) {
		Player homePlayer = playerRepository.findById(matchDTO.getHomePlayer())
				.orElseThrow(() -> new NotFoundException("Home player not found"));

		Player awayPlayer = playerRepository.findById(matchDTO.getAwayPlayer())
				.orElseThrow(() -> new NotFoundException("Away player not found"));

		if (homePlayer.getId() == awayPlayer.getId()) {
			throw new IllegalArgumentException("Home and away players cannot have the same id");
		}

		matchModel.setHomePlayer(homePlayer);
		matchModel.setAwayPlayer(awayPlayer);
		matchModel.setHomePlayerGoals(matchDTO.getHomePlayerGoals());
		matchModel.setAwayPlayerGoals(matchDTO.getAwayPlayerGoals());

		return matchRepository.save(matchModel);
	}

	public List<Match> findAll() {
		return matchRepository.findAll();
	}

	public Match findById(UUID id) {
		return matchRepository.findById(id).orElseThrow(() -> new NotFoundException("Match not found"));
	}

	public Match create(MatchDTO playerMatchDTO) {
		Match playerMatchModel = new Match();
		return getAndSavePlayerMatchObject(playerMatchDTO, playerMatchModel);
	}

	public Object update(UUID id, MatchDTO playerMatchDTO) {
		Match playerMatchModel = matchRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Match not found"));
		return getAndSavePlayerMatchObject(playerMatchDTO, playerMatchModel);
	}

	public Object patch(UUID id, Map<String, Object> parcialData) {
		Match playerMatchModel = matchRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Match not found"));
		mergeData(parcialData, playerMatchModel);
		playerMatchModel = matchRepository.save(playerMatchModel);
		return playerMatchModel;
	}

	public void delete(UUID id) {
		Match playerMatch = matchRepository.findById(id).orElseThrow(() -> new NotFoundException("Match not found"));
		matchRepository.deleteById(playerMatch.getId());
	}

}
