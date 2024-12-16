package com.example.labpsql.services.impl;

import com.example.labpsql.models.Country;
import com.example.labpsql.repositories.CountryRepository;
import com.example.labpsql.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CacheManager cacheManager;
    private static final String CACHE_NAME = "countries";

    @Override
    @CachePut(CACHE_NAME)
    public Country saveCountry(String name) {
        return countryRepository.save(new Country(name));
    }

    @Override
    public List<Country> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        if (countries.isEmpty()) {
            throw new RuntimeException("No countries found");
        }

        Cache cache = cacheManager.getCache(CACHE_NAME);
        if (cache != null) {
            countries.forEach(country -> cache.put(country.getName(), country));
        }
        return countries;
    }

    @Override
    @Cacheable(CACHE_NAME)
    public Country findByName(String name) {
        return countryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }
}
