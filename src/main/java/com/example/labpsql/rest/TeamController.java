package com.example.labpsql.rest;

import com.example.labpsql.dto.request.AddTeamRequest;
import com.example.labpsql.models.Team;
import com.example.labpsql.services.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody @Valid AddTeamRequest request) {
        return ResponseEntity.ok(teamService.saveTeam(request));
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }
}
