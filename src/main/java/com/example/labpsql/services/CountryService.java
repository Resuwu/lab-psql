package com.example.labpsql.services;

import com.example.labpsql.models.Country;

import java.util.List;

public interface CountryService {
    Country saveCountry(String name);

    List<Country> getAllCountries();

    Country findByName(String name);
}
