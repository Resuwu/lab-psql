package com.example.labpsql.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ObjectTypes {
    COUNTRY("Country"),
    TEAM("Team"),
    PLAYER("Player"),
    RECORD("Record"),
    SUBJECT("Subject"),
    TEAM_COMPOSITION("Team Composition");

    public final String name;
}
