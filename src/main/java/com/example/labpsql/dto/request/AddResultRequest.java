package com.example.labpsql.dto.request;

import com.example.labpsql.models.*;
import lombok.Builder;
import lombok.Data;

import java.time.Year;
import java.util.List;

@Data
@Builder
public class AddResultRequest {
    private Year year;
    private Subject subject;
    private Gender gender;
    private String result;
    private String location;
    private RecordType recordType;
    private List<Player> players;
}
