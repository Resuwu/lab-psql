package com.example.labpsql.services.impl;

import com.example.labpsql.models.Country;
import com.example.labpsql.repositories.CountryRepository;
import com.example.labpsql.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public Country saveCountry(String name) {
        return countryRepository.save(new Country(name));
    }

    @Override
    public List<Country> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        if (countries.isEmpty()) {
            throw new RuntimeException("No countries found");
        }
        return countries;
    }

    @Override
    public Country findByName(String name) {
        return countryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }
}
