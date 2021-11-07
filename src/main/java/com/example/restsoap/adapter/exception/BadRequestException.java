package com.example.restsoap.adapter.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("BAD REQUEST");
    }

    public BadRequestException(String msg) {
        super("BAD REQUEST: " + msg);
    }
    public BadRequestException(List<ObjectError> errorList) {
        super("BAD REQUEST:\n" );
    }
    private String mapObjectErrorListToString(List<ObjectError> errorList){
        StringBuilder errorsMessage = new StringBuilder();
        for (ObjectError error : errorList) {
            errorsMessage.append(error.getObjectName())
                    .append(error.getDefaultMessage());
        }
        return errorsMessage.toString();
    }
}