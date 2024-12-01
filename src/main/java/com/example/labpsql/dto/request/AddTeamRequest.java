package com.example.labpsql.dto.request;

import com.example.labpsql.models.Country;
import com.example.labpsql.models.Subject;
import com.example.labpsql.models.TeamStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AddTeamRequest {
    private String name;
    private Country country;
    private LocalDate foundedAt;
    private Subject subject;
    private TeamStatus status;
    private String managerName;
}
