package com.example.restsoap.adapter.controller;

import com.example.restsoap.adapter.service.CalculatorService;
import com.example.restsoap.adapter.model.Operation;
import com.example.restsoap.adapter.utils.ObjectErrorMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/calculator")
public class CalculatorController {
    private CalculatorService calculatorService;

    private ObjectErrorMapper mapper;

    public CalculatorController(
            @Qualifier("soapCalculatorService") CalculatorService calculatorService,
            ObjectErrorMapper mapper) {
        this.calculatorService = calculatorService;
        this.mapper = mapper;
    }
    @GetMapping("/add")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Adds two integer values",
            description = "Execute SOAP add request if there is no execution result in the cache yet " +
                    "or takes the result from the cache, if it is present there "
    )
    public int addOperation(@Valid Operation operation,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }

        return calculatorService.add(
                operation.getFirstNumber(),
                operation.getSecondNumber()
        );
    }

    @GetMapping(value = "/divide")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Divides firstNumber by secondNumber",
            description = "Execute SOAP divide request if there is no execution result in the cache yet " +
                    "or takes the result from the cache, if it is present there"
    )
    public int divideOperation(@Valid Operation operation,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw mapper.toBadRequestException(bindingResult.getAllErrors());

        return calculatorService.divide(
                operation.getFirstNumber(),
                operation.getSecondNumber()
        );
    }

    @GetMapping(value = "/multiply")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Multiply two integer values",
            description = "Execute SOAP multiply request if there is no execution result in the cache yet " +
                    "or takes the result from the cache, if it is present there"
    )
    public int multiplyOperation(@Valid Operation operation,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw mapper.toBadRequestException(bindingResult.getAllErrors());

        return calculatorService.multiply(
                operation.getFirstNumber(),
                operation.getSecondNumber()
        );
    }

    @GetMapping(value = "/subtract")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Subtracts the secondNumber from the firstNumber",
            description = "Execute SOAP divide request if there is no execution result in the cache yet " +
                    "or takes the result from the cache, if it is present there"
    )
    public int subtractOperation(@Valid Operation operation,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw mapper.toBadRequestException(bindingResult.getAllErrors());

        return calculatorService.subtract(
                operation.getFirstNumber(),
                operation.getSecondNumber()
        );
    }
}