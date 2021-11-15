package com.example.restsoap.adapter.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class Operation {
    @NotNull(message = "must be not null")
    Integer firstNumber;
    @NotNull(message = "must be not null")
    Integer secondNumber;

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }
}