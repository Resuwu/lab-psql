package com.example.labpsql.auth.repositories;

import com.example.labpsql.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    public boolean existsByUsername(String username);
    public Optional<User> findByUsername(String username);
}
