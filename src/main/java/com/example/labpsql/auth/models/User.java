package com.example.labpsql.auth.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Table(name = "users")
@Getter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private List<Role> userRoles;

    @Override
    public Set<Authority> getAuthorities() {
        Set<Authority> authorityMatrix = new HashSet<>();
        userRoles.forEach(role -> authorityMatrix.addAll(role.getAuthorities()));
        return authorityMatrix;
    }

    public boolean hasAuthority(Authority authority) {
        return getAuthorities().contains(authority);
    }
}
