package com.example.labpsql.configs;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationUtil {
    private final Validator validator;

    public <E> boolean isNotValid(E object) {
        return !this.validator.validate(object).isEmpty();
    }

    public <E> Set<ConstraintViolation<E>> violations(E object) {
        return this.validator.validate(object);
    }
}
