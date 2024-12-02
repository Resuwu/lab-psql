package com.example.labpsql.dto.request;

import com.example.labpsql.models.Country;
import com.example.labpsql.models.Subject;
import com.example.labpsql.models.TeamStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AddTeamRequest {
    @NotNull
    private String name;
    @NotNull
    private Country country;
    @NotNull
    private LocalDate foundedAt;
    @NotNull
    private Subject subject;
    @NotNull
    private TeamStatus status;
    @NotEmpty
    private String managerName;
}
