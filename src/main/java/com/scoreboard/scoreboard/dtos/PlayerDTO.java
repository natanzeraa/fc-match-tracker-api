package com.scoreboard.scoreboard.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private UUID id;
    
    @NotBlank(message = "A player name must be provided")
    private String name;
    
    @NotBlank(message = "A valid email must be provided")
    @Email(message = "Email should be valid")
    private String email;

}
