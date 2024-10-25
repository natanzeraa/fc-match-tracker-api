package com.scoreboard.scoreboard.repositories;

import org.springframework.stereotype.Repository;
import com.scoreboard.scoreboard.models.PlayerMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, UUID> {
}
