package com.example.labpsql.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddSubjectRequest {
    @NotNull
    private String name;
    private String sport;
}
