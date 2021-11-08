package com.example.restsoap.adapter.controller;

import com.example.restsoap.adapter.service.CalculatorService;
import com.example.restsoap.adapter.model.Operation;
import com.example.restsoap.adapter.utils.ObjectErrorMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/calculator")
@Tag(name = "Calculator operations controller")
public class CalculatorController {

    private CalculatorService calculatorService;

    private Validator calculatorValidator;

    private ObjectErrorMapper mapper;

    public CalculatorController(
            @Qualifier("calculatorValidator") Validator calculatorValidator,
            @Qualifier("soapCalculatorService") CalculatorService calculatorService,
            ObjectErrorMapper mapper) {
        this.calculatorValidator = calculatorValidator;
        this.calculatorService = calculatorService;
        this.mapper = mapper;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(calculatorValidator);
    }

    @PostMapping("/add")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Adds two integer values",
            description = "Execute SOAP add request if there is no execution result in the cache yet " +
                    "or takes the result from the cache, if it is present there "
    )
    public int addOperation(@RequestBody @Valid Operation operation,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }

        return calculatorService.add(
                Integer.parseInt(operation.getFirstNumber()),
                Integer.parseInt(operation.getSecondNumber())
        );
    }

    @PostMapping(value = "/divide")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Divides firstNumber by secondNumber",
            description = "Execute SOAP divide request if there is no execution result in the cache yet " +
                    "or takes the result from the cache, if it is present there"
    )
    public int divideOperation(@RequestBody @Valid Operation operation,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }

        return calculatorService.divide(
                Integer.parseInt(operation.getFirstNumber()),
                Integer.parseInt(operation.getSecondNumber())
        );
    }

    @PostMapping(value = "/multiply")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Multiply two integer values",
            description = "Execute SOAP multiply request if there is no execution result in the cache yet " +
                    "or takes the result from the cache, if it is present there"
    )
    public int multiplyOperation(@RequestBody @Valid Operation operation,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }

        return calculatorService.multiply(
                Integer.parseInt(operation.getFirstNumber()),
                Integer.parseInt(operation.getSecondNumber())
        );
    }

    @PostMapping(value = "/subtract")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Subtracts the secondNumber from the firstNumber",
            description = "Execute SOAP divide request if there is no execution result in the cache yet " +
                    "or takes the result from the cache, if it is present there"
    )
    public int subtractOperation(@RequestBody @Valid Operation operation,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw mapper.toBadRequestException(bindingResult.getAllErrors());
        }

        return calculatorService.subtract(
                Integer.parseInt(operation.getFirstNumber()),
                Integer.parseInt(operation.getSecondNumber())
        );
    }

}