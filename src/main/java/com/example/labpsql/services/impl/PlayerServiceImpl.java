package com.example.labpsql.services.impl;

import com.example.labpsql.configs.ValidationUtil;
import com.example.labpsql.dto.request.AddPlayerRequest;
import com.example.labpsql.models.Player;
import com.example.labpsql.repositories.PlayerRepository;
import com.example.labpsql.services.PlayerService;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Player savePlayer(AddPlayerRequest request) {
        if (validationUtil.isNotValid(request)) {
            validationUtil
                    .violations(request)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return null;
        }
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
}
