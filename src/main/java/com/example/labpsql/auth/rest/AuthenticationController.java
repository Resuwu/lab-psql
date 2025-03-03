package com.example.labpsql.auth.rest;

import com.example.labpsql.auth.dto.JwtAuthenticationResponse;
import com.example.labpsql.auth.dto.SignInRequest;
import com.example.labpsql.auth.dto.SignUpRequest;
import com.example.labpsql.auth.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody @Valid SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("signInRequest") SignInRequest signInRequest, Model model) {
        authenticationService.signIn(signInRequest);
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("signUpRequest") SignUpRequest signUpRequest, Model model) {
        authenticationService.signUp(signUpRequest);
        return "redirect:/";
    }
}
