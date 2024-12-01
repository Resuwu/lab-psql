package com.example.labpsql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team extends BaseEntity {
    private String name;
    @ManyToOne
    private Country country;
    private LocalDate foundedAt;
    @ManyToOne
    private Subject subject;
    private TeamStatus status;
    private String managerName;
}
