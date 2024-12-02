package com.example.labpsql.dto.request;

import com.example.labpsql.models.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Year;
import java.util.List;

@Data
@Builder
public class AddResultRequest {
    @NotNull
    private Year year;
    @NotNull
    private Subject subject;
    @NotNull
    private Gender gender;
    @NotNull
    private String result;
    @NotNull
    private String location;
    @NotNull
    private RecordType recordType;
    @NotNull
    private List<Player> players;
}
