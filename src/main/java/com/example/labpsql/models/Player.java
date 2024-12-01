package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "players")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Player extends BaseEntity {
    private String name;
    @ManyToOne
    private Country country;
    private Gender gender;
    private LocalDate birthDate;
    @ManyToOne
    private Subject subject;
}
