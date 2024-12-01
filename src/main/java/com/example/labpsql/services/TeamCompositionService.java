package com.example.labpsql.services;

import com.example.labpsql.dto.request.AddTeamCompositionRequest;
import com.example.labpsql.models.TeamComposition;

import java.util.List;

public interface TeamCompositionService {
    TeamComposition saveTeamComposition(AddTeamCompositionRequest request);

    List<TeamComposition> getAllTeamCompositions();

    TeamComposition findById(String id);
}
