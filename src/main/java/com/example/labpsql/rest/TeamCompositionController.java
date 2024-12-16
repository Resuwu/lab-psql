package com.example.labpsql.rest;

import com.example.labpsql.dto.request.AddTeamCompositionRequest;
import com.example.labpsql.models.TeamComposition;
import com.example.labpsql.services.TeamCompositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team-composition")
public class TeamCompositionController {
    private final TeamCompositionService teamCompositionService;

    @PostMapping
    public ResponseEntity<TeamComposition> createTeamComposition(@RequestBody @Valid AddTeamCompositionRequest request) {
        return ResponseEntity.ok(teamCompositionService.saveTeamComposition(request));
    }

    @GetMapping
    public ResponseEntity<List<TeamComposition>> getAllTeamCompositions() {
        return ResponseEntity.ok(teamCompositionService.getAllTeamCompositions());
    }
}
