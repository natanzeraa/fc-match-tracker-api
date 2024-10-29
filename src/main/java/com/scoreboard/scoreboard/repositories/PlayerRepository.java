package com.scoreboard.scoreboard.repositories;


import com.scoreboard.scoreboard.models.Player;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
	boolean existsByEmail(String email);
}

