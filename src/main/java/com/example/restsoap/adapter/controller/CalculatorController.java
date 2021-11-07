package com.example.restsoap.adapter.controller;


import com.example.restsoap.adapter.exception.BadRequestException;
import com.example.restsoap.adapter.model.Operation;
import com.example.restsoap.adapter.service.CalculatorService;
import com.example.restsoap.adapter.utils.ObjectErrorMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/calculator")
public class CalculatorController {

    private CalculatorService calculatorService;

    private Validator calculatorValidator;

    private ObjectErrorMapper mapper;

    public CalculatorController(
            @Qualifier("calculatorValidator") Validator calculatorValidator,
            @Qualifier("soapCalculatorService") CalculatorService calculatorService,
            ObjectErrorMapper mapper){
        this.calculatorValidator = calculatorValidator;
        this.calculatorService = calculatorService;
        this.mapper = mapper;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(calculatorValidator);
    }

    @PostMapping("/add")
    public int addOperation(
            @RequestBody @Valid Operation operation,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }

        return calculatorService.add(
                Integer.parseInt(operation.getFirstNumber()),
                Integer.parseInt(operation.getSecondNumber())
        );
    }

    @PostMapping(value = "/divide")
    public int divideOperation(@RequestBody @Valid Operation operation,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }
        return calculatorService.divide(
                Integer.parseInt(operation.getFirstNumber()),
                Integer.parseInt(operation.getSecondNumber())
        );
    }

    @PostMapping(value = "/multiply")
    public int multiplyOperation(@RequestBody @Valid Operation operation,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }

        return calculatorService.multiply(
                Integer.parseInt(operation.getFirstNumber()),
                Integer.parseInt(operation.getSecondNumber())
        );
    }

    @PostMapping(value = "/subtract")
    public int subtractOperation(@RequestBody @Valid Operation operation,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }

        return calculatorService.subtract(
                Integer.parseInt(operation.getFirstNumber()),
                Integer.parseInt(operation.getSecondNumber())
        );
    }

}
