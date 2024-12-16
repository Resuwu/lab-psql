package com.example.labpsql.rest;

import com.example.labpsql.dto.request.AddResultRequest;
import com.example.labpsql.models.Result;
import com.example.labpsql.services.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
public class ResultController {
    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody @Valid AddResultRequest request) {
        return ResponseEntity.ok(resultService.saveResult(request));
    }

    @GetMapping
    public ResponseEntity<List<Result>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Result>> getAllResultsByYear(@PathVariable Year year) {
        return ResponseEntity.ok(resultService.findAllByYear(year));
    }
}
