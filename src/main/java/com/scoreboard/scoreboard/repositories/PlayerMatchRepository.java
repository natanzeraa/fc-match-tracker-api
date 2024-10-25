package com.scoreboard.scoreboard.repositories;

import com.scoreboard.scoreboard.models.PlayerMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, UUID> {
}
