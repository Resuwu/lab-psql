package com.example.labpsql.services;

import com.example.labpsql.dto.request.AddTeamRequest;
import com.example.labpsql.models.Team;

import java.util.List;

public interface TeamService {
    Team saveTeam(AddTeamRequest request);

    List<Team> getAllTeams();

    Team findByName(String name);
}
