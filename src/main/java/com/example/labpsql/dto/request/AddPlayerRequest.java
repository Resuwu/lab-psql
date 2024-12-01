package com.example.labpsql.dto.request;

import com.example.labpsql.models.Country;
import com.example.labpsql.models.Gender;
import com.example.labpsql.models.Subject;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AddPlayerRequest {
    private String name;
    private Country country;
    private Gender gender;
    private LocalDate birthDate;
    private Subject subject;
}