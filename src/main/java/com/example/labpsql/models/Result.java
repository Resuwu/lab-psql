package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Year;
import java.util.List;

@Entity
@Table(name = "results")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result extends BaseEntity {
    private Year year;
    @ManyToOne
    private Subject subject;
    private Gender gender;
    private String result;
    private String location;
    private RecordType recordType;
    @ManyToMany
    private List<Player> players;
}
