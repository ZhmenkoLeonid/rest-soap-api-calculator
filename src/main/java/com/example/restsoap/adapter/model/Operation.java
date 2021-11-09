package com.example.restsoap.adapter.model;

import org.springframework.stereotype.Component;

@Component
public class Operation {
    Integer firstNumber;
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