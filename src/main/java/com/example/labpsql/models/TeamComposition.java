package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Year;

@Entity
@Table(name = "team_compositions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class TeamComposition extends BaseEntity {
    @ManyToOne
    private Team team;
    private Year year;
    @ManyToOne
    private Player player;
    private String description;
}
