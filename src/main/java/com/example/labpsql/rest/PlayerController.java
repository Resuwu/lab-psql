package com.example.labpsql.rest;

import com.example.labpsql.dto.request.AddPlayerRequest;
import com.example.labpsql.models.Player;
import com.example.labpsql.services.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody @Valid AddPlayerRequest request) {
        return ResponseEntity.ok(playerService.savePlayer(request));
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }
}
