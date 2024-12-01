package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Year;

@Entity
@Table(name = "team_compositions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamComposition extends BaseEntity {
    @ManyToOne
    private Team team;
    private Year year;
    @ManyToOne
    private Player player;
    private String description;
}
