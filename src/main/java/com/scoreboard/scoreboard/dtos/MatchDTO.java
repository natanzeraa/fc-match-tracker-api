package com.scoreboard.scoreboard.dtos;

import lombok.*;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {

    private UUID id;
    private String title;
}
