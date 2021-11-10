package com.example.restsoap.adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.soap.client.SoapFaultClientException;

@ControllerAdvice
public class CalculatorExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String defaultExceptionHandler(Exception ex) {
        System.out.println(ex.getClass().toString());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SoapFaultClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private String soapExceptionHandler(SoapFaultClientException ex) {
        return "SOAP server internal error: "+ ex.getMessage();
    }
}