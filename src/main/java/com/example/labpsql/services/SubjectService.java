package com.example.labpsql.services;

import com.example.labpsql.dto.request.AddSubjectRequest;
import com.example.labpsql.models.Subject;

import java.util.List;

public interface SubjectService {
    Subject saveSubject(AddSubjectRequest request);

    List<Subject> getAllSubjects();
}
