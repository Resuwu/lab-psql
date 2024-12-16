package com.example.labpsql.auth.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
