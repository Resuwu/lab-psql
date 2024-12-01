package com.example.labpsql.dto.request;

import com.example.labpsql.models.Player;
import com.example.labpsql.models.Team;
import lombok.Builder;
import lombok.Data;

import java.time.Year;

@Data
@Builder
public class AddTeamCompositionRequest {
    private Team team;
    private Year year;
    private Player player;
    private String description;
}
