package com.example.labpsql.services.impl;

import com.example.labpsql.configs.ValidationUtil;
import com.example.labpsql.dto.request.AddTeamCompositionRequest;
import com.example.labpsql.models.TeamComposition;
import com.example.labpsql.repositories.TeamCompositionRepository;
import com.example.labpsql.services.TeamCompositionService;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamCompositionServiceImpl implements TeamCompositionService {
    private final TeamCompositionRepository teamCompositionRepository;
    private final ValidationUtil validationUtil;

    @Override
    public TeamComposition saveTeamComposition(AddTeamCompositionRequest request) {
        if (validationUtil.isNotValid(request)) {
            validationUtil
                    .violations(request)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return null;
        }
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
}
