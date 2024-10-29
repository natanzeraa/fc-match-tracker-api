package com.scoreboard.scoreboard.models;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.util.UUID;
import java.time.Instant;
import java.io.Serializable;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="matches")
public class Match implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "home_player_id")
    private Player homePlayer;

    @ManyToOne
    @JoinColumn(name = "away_player_id")
    private Player awayPlayer;

    @Column(nullable = false)
    private Integer homePlayerGoals;

    @Column(nullable = false)
    private Integer awayPlayerGoals;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdate;
}
