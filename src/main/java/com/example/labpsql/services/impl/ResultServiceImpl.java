package com.example.labpsql.services.impl;

import com.example.labpsql.configs.ValidationUtil;
import com.example.labpsql.dto.request.AddResultRequest;
import com.example.labpsql.models.Result;
import com.example.labpsql.repositories.ResultRepository;
import com.example.labpsql.services.ResultService;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Result saveResult(AddResultRequest request) {
        if (validationUtil.isNotValid(request)) {
            validationUtil
                    .violations(request)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return null;
        }
        Result result = Result.builder()
                .year(request.getYear())
                .subject(request.getSubject())
                .gender(request.getGender())
                .result(request.getResult())
                .location(request.getLocation())
                .recordType(request.getRecordType())
                .players(request.getPlayers())
                .build();

        return resultRepository.save(result);
    }

    @Override
    public List<Result> getAllResults() {
        List<Result> results = resultRepository.findAll();
        if (results.isEmpty()) {
            throw new RuntimeException("No results found in the database");
        }
        return results;
    }

    @Override
    public List<Result> findAllByYear(Year year) {
        List<Result> result = resultRepository.findAllByYear(year);
        if (result.isEmpty()) {
            throw new RuntimeException("No results found for the given year");
        }
        return result;
    }
}
