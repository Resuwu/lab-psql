package com.example.labpsql.services.impl;

import com.example.labpsql.dto.request.AddPlayerRequest;
import com.example.labpsql.models.Player;
import com.example.labpsql.repositories.PlayerRepository;
import com.example.labpsql.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public Player savePlayer(AddPlayerRequest request) {
        Player player = new Player(
                request.getName(),
                request.getCountry(),
                request.getGender(),
                request.getBirthDate(),
                request.getSubject());

        return playerRepository.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new RuntimeException("No players found");
        }
        return players;
    }

    @Override
    public Player findByName(String name) {
        return playerRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }
}
