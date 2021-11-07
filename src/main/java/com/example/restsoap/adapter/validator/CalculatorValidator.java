package com.example.restsoap.adapter.validator;

import com.example.restsoap.adapter.model.Operation;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class CalculatorValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Operation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Operation operation = (Operation) target;
        fieldValidate("firstNumber",operation.getFirstNumber(),errors);
        fieldValidate("secondNumber",operation.getSecondNumber(),errors);
    }

    private void fieldValidate(String fieldName, String fieldValue, Errors errors){
        if (fieldValue == null) {
            errors.rejectValue(fieldName, "value.null", "value can't be null");
        } else if (fieldValue.equals("")) {
            errors.rejectValue(fieldName, "value.empty", "value can't be empty");
        } else if (!isInteger(fieldValue)) {
            errors.rejectValue(fieldName, "value.not.int", "value must be int");
        }
    }

    private boolean isInteger(String str)
    {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
}