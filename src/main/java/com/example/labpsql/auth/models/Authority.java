package com.example.labpsql.auth.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Authority implements GrantedAuthority {
    PLAYERS("Игроки"),
    RESULTS("Результаты"),
    TEAMS("Команды"),
    COUNTRIES("Страны"),
    TEAM_COMPOSITIONS("Составы команд"),
    SUBJECTS("Дисциплины");

    private final String authority;
}
