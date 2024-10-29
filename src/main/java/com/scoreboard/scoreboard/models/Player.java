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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player implements Serializable {
	@Serial
	private static final long serialVersionUID = -1000754471980702889L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false, length = 255)
	@NotBlank(message = "Name is required")
	private String name;

	@Column(nullable = false, unique = true, length = 255)
	@NotBlank(message = "Email is required")
	private String email;

	@CreationTimestamp
	@Column(nullable = false)
	private Instant createdOn;

	@UpdateTimestamp
	private Instant lastUpdate;

}
