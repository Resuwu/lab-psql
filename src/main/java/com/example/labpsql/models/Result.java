package com.example.labpsql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Entity
@Table(name = "results")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result extends BaseEntity {
    private Year year;
    @ManyToOne
    private Subject subject;
    private Gender gender;
    private String result;
    private String location;
    private RecordType recordType;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Player> players;

    @Override
    public String toString() {
        String string = String.format("""
                        {
                            year=%s,
                            subject=%s,
                            gender=%s,
                            result='%s',
                            location='%s',
                            recordType=%s,
                            players=%s
                        },
                        """,
                year, subject, gender, result, location, recordType, players
        );

        return string;
    }
}
