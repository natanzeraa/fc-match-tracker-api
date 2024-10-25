package com.scoreboard.scoreboard.dtos;

import lombok.*;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerMatchDTO {

    private UUID id;
    private PlayerDTO homePlayer;
    private PlayerDTO awayPlayer;
    private MatchDTO match;
    private Integer homePlayerGoals;
    private Integer awayPlayerGoals;
}
