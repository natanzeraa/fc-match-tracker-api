package com.scoreboard.scoreboard.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.UUID;
import java.time.Instant;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdate;

}
