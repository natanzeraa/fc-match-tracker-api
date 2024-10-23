package com.scoreboard.scoreboard.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlayerRecord(@NotBlank String name, @NotNull String email) {}
