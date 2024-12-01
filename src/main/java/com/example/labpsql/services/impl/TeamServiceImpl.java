package com.example.labpsql.services.impl;

import com.example.labpsql.dto.request.AddTeamRequest;
import com.example.labpsql.models.Team;
import com.example.labpsql.repositories.TeamRepository;
import com.example.labpsql.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team saveTeam(AddTeamRequest request) {
        Team team = new Team(
                request.getName(),
                request.getCountry(),
                request.getFoundedAt(),
                request.getSubject(),
                request.getStatus(),
                request.getManagerName());

        return teamRepository.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        if (teams.isEmpty()) {
            throw new RuntimeException("No teams found");
        }
        return teams;
    }

    @Override
    public Team findByName(String name) {
        return teamRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }
}
