package com.example.labpsql.dto.request;

import com.example.labpsql.models.Player;
import com.example.labpsql.models.Team;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Year;

@Data
@Builder
public class AddTeamCompositionRequest {
    @NotNull
    private Team team;
    @NotNull
    private Year year;
    @NotNull
    private Player player;
    @NotEmpty
    private String description;
}
