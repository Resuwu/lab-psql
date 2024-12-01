package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "subjects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subject extends BaseEntity {
    private String name;
    private String sport;
}
