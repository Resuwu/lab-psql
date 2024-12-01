package com.example.labpsql.services;

import com.example.labpsql.dto.request.AddResultRequest;
import com.example.labpsql.models.Result;

import java.time.Year;
import java.util.List;

public interface ResultService {
    Result saveResult(AddResultRequest request);

    List<Result> getAllResults();

    List<Result> findAllByYear(Year year);
}
