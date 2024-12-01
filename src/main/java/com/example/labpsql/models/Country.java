package com.example.labpsql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "countries")
@ToString
@NoArgsConstructor
public class Country extends BaseEntity {
    private String name;

    public Country(String name) {
        this.name = name;
    }
}
