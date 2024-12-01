package com.example.labpsql.services.impl;

import com.example.labpsql.dto.request.AddTeamCompositionRequest;
import com.example.labpsql.models.TeamComposition;
import com.example.labpsql.repositories.TeamCompositionRepository;
import com.example.labpsql.services.TeamCompositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamCompositionServiceImpl implements TeamCompositionService {
    private final TeamCompositionRepository teamCompositionRepository;

    @Override
    public TeamComposition saveTeamComposition(AddTeamCompositionRequest request) {
        TeamComposition teamComposition = TeamComposition.builder()
                .team(request.getTeam())
                .year(request.getYear())
                .player(request.getPlayer())
                .description(request.getDescription())
                .build();

        return teamCompositionRepository.save(teamComposition);
    }

    @Override
    public List<TeamComposition> getAllTeamCompositions() {
        List<TeamComposition> teamCompositions = teamCompositionRepository.findAll();
        if (teamCompositions.isEmpty()) {
            throw new RuntimeException("No team compositions found");
        }
        return teamCompositions;
    }

    @Override
    public TeamComposition findById(String id) {
        return teamCompositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team composition not found with id: " + id));
    }
}
