package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
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
