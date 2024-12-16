package com.example.labpsql.rest;

import com.example.labpsql.dto.request.AddSubjectRequest;
import com.example.labpsql.models.Subject;
import com.example.labpsql.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody @Valid AddSubjectRequest request) {
        return ResponseEntity.ok(subjectService.saveSubject(request));
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }
}
