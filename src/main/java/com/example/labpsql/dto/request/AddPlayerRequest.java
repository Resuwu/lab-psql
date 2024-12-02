package com.example.labpsql.dto.request;

import com.example.labpsql.models.Country;
import com.example.labpsql.models.Gender;
import com.example.labpsql.models.Subject;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AddPlayerRequest {
    @NotNull
    private String name;
    @NotNull
    private Country country;
    @NotNull
    private Gender gender;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private Subject subject;
}