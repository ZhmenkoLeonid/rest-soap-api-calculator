package com.example.restsoap.adapter.utils;

import com.example.restsoap.adapter.exception.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectErrorMapper {
    public BadRequestException toBadRequestException(List<ObjectError> errors) {
        return new BadRequestException(
                errors.stream()
                        .map(error -> error instanceof FieldError ?
                                ((FieldError) error).getField() + " : " + error.getDefaultMessage() :
                                error.getDefaultMessage())
                        .collect(Collectors.joining("\n"))
        );
    }
}