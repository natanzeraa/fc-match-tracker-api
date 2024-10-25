package com.scoreboard.scoreboard.models;

import lombok.Data;
import jakarta.persistence.*;
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
@Table(name = "matches")
public class Match implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdate;

    public Match(Match match) {
    }
}
