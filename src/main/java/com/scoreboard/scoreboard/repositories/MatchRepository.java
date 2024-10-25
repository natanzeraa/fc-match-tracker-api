package com.scoreboard.scoreboard.repositories;

import com.scoreboard.scoreboard.models.Match;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> { }
