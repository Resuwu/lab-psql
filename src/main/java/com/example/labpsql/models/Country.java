package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "countries")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Country extends BaseEntity {
    private String name;
}
