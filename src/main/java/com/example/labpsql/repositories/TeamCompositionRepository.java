package com.example.labpsql.repositories;

import com.example.labpsql.models.TeamComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamCompositionRepository extends JpaRepository<TeamComposition, String> {
}
