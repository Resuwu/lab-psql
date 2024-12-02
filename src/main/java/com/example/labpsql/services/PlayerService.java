package com.example.labpsql.services;

import com.example.labpsql.dto.request.AddPlayerRequest;
import com.example.labpsql.models.Player;

import java.util.List;

public interface PlayerService {
    Player savePlayer(AddPlayerRequest request);

    List<Player> getAllPlayers();
}
