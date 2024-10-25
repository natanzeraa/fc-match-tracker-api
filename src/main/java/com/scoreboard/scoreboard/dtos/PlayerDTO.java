package com.scoreboard.scoreboard.dtos;


import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private UUID id;
    private String name;
    private String email;

}
