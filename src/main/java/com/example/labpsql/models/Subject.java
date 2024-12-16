package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "subjects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Subject extends BaseEntity {
    private String name;
    private String sport;
}
