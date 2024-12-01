package com.example.labpsql.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddSubjectRequest {
    private String name;
    private String sport;
}
