package com.scoreboard.scoreboard.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {

	private UUID id;

	@NotNull(message = "Home player id cannot be empty")
	private UUID homePlayer;

	@NotNull(message = "Away player id cannot be empty")
	private UUID awayPlayer;

    @NotNull(message = "Home player goals cannot be empty")
    @Min(value = 0, message = "Home player goals cannot be less than 0")
	private Integer homePlayerGoals;

    @NotNull(message = "Away player goals cannot be empty")
    @Min(value = 0, message = "Away player goals cannot be less than 0")
	private Integer awayPlayerGoals;
}
