package com.example.labpsql.auth.services;

import com.example.labpsql.auth.dto.JwtAuthenticationResponse;
import com.example.labpsql.auth.dto.SignInRequest;
import com.example.labpsql.auth.dto.SignUpRequest;
import com.example.labpsql.auth.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CacheManager cacheManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userService.create(user);
        String token = jwtTokenService.generateToken(user);
        return new JwtAuthenticationResponse(token);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = userService.userDetailsService().loadUserByUsername(request.getUsername());
        String token = jwtTokenService.generateToken(user);
        return new JwtAuthenticationResponse(token);
    }
}
