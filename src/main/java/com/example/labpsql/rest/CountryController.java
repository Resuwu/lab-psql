package com.example.labpsql.rest;

import com.example.labpsql.models.Country;
import com.example.labpsql.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<Country> createCountry(String name) {
        return ResponseEntity.ok(countryService.saveCountry(name));
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
        return ResponseEntity.ok(countryService.findByName(name));
    }
}
