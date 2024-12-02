package com.example.labpsql.services.impl;

import com.example.labpsql.configs.ValidationUtil;
import com.example.labpsql.dto.request.AddSubjectRequest;
import com.example.labpsql.models.Subject;
import com.example.labpsql.repositories.SubjectRepository;
import com.example.labpsql.services.SubjectService;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Subject saveSubject(AddSubjectRequest request) {
        if (!validationUtil.isValid(request)) {
            validationUtil
                    .violations(request)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return null;
        }
        Subject subject = Subject.builder()
                .name(request.getName())
                .sport(request.getSport())
                .build();

        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        if (subjects.isEmpty()) {
            throw new RuntimeException("No subjects found");
        }
        return subjects;
    }
}
