package com.example.labpsql.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddSubjectRequest {
    @NotEmpty
    private String name;
    private String sport;
}
