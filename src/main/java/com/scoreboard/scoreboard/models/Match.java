package com.scoreboard.scoreboard.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
public class Match implements Serializable {

	@Serial
	private static final long serialVersionUID = 3488025702985987082L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "home_player_id", nullable = false)
	private Player homePlayer;

	@ManyToOne
	@JoinColumn(name = "away_player_id", nullable = false)
	private Player awayPlayer;

	@Column(nullable = false)
	@NotNull(message = "Goals cannot be empty")
    @Min(value = 0, message = "Goals cannot be less than 0")
	private Integer homePlayerGoals;

	@Column(nullable = false)
	@NotNull(message = "Goals cannot be empty")
    @Min(value = 0, message = "Goals cannot be less than 0")
	private Integer awayPlayerGoals;

	@CreationTimestamp
	@Column(nullable = false)
	private Instant createdOn;

	@UpdateTimestamp
	private Instant lastUpdate;
}
