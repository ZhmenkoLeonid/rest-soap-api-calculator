package com.example.restsoap.adapter;

import com.example.restsoap.adapter.model.Operation;
import com.example.restsoap.adapter.service.CalculatorService;
import com.example.restsoap.adapter.validator.CalculatorValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestSoapAdapterApplicationTests {

    @Autowired
    @Qualifier("testSOAPCalculatorService")
    private CalculatorService service;

    @Autowired
    private CalculatorValidator validator;

    DataBinder dataBinder;

    Operation operation;

    @Before
    public void prepare(){
        operation = new Operation();
        dataBinder = new DataBinder(operation);
        dataBinder.addValidators(validator);
    }

    @Test
    public void normalSituationValidatorTest(){
        operation.setFirstNumber("5");
        operation.setSecondNumber("5");

        dataBinder.validate(operation);

        Assert.assertFalse(dataBinder.getBindingResult().hasErrors());
    }

    @Test
    public void nullValueValidatorTest(){
        // 1 null value
        operation.setFirstNumber(null);
        operation.setSecondNumber("5");

        dataBinder.validate(operation);

        List<ObjectError> errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(1, errorList.size());
        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        Assert.assertEquals("firstNumber", ((FieldError) errorList.get(0)).getField());
        Assert.assertEquals("value can't be null", errorList.get(0).getDefaultMessage());

        // 2 null values

        operation = new Operation();
        operation.setFirstNumber(null);
        operation.setSecondNumber(null);

        dataBinder = new DataBinder(operation);
        dataBinder.addValidators(validator);
        dataBinder.validate(operation);
        errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(2, errorList.size());

        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        FieldError firstNumberError = (FieldError) errorList.get(0);
        Assert.assertEquals("firstNumber", firstNumberError.getField());
        Assert.assertEquals("value can't be null", firstNumberError.getDefaultMessage());

        Assert.assertTrue(errorList.get(1) instanceof FieldError);
        FieldError secondNumberError = (FieldError) errorList.get(1);
        Assert.assertEquals("secondNumber", secondNumberError.getField());
        Assert.assertEquals("value can't be null", secondNumberError.getDefaultMessage());
    }

    @Test
    public void emptyValueValidatorTest(){
        // 1 empty value
        operation.setFirstNumber("");
        operation.setSecondNumber("5");

        dataBinder.validate(operation);

        List<ObjectError> errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(1, errorList.size());
        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        Assert.assertEquals("firstNumber", ((FieldError) errorList.get(0)).getField());
        Assert.assertEquals("value can't be empty", errorList.get(0).getDefaultMessage());

        // 2 empty values

        operation = new Operation();
        operation.setFirstNumber("");
        operation.setSecondNumber("");

        dataBinder = new DataBinder(operation);
        dataBinder.addValidators(validator);
        dataBinder.validate(operation);
        errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(2, errorList.size());

        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        FieldError firstNumberError = (FieldError) errorList.get(0);
        Assert.assertEquals("firstNumber", firstNumberError.getField());
        Assert.assertEquals("value can't be empty", firstNumberError.getDefaultMessage());

        Assert.assertTrue(errorList.get(1) instanceof FieldError);
        FieldError secondNumberError = (FieldError) errorList.get(1);
        Assert.assertEquals("secondNumber", secondNumberError.getField());
        Assert.assertEquals("value can't be empty", secondNumberError.getDefaultMessage());
    }

    @Test
    public void notIntValueValidatorTest(){
        // 1 not int value
        operation.setFirstNumber("Пять");
        operation.setSecondNumber("5");

        dataBinder.validate(operation);

        List<ObjectError> errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(1, errorList.size());
        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        Assert.assertEquals("firstNumber", ((FieldError) errorList.get(0)).getField());
        Assert.assertEquals("value must be int", errorList.get(0).getDefaultMessage());

        // 2 not int values

        operation = new Operation();
        operation.setFirstNumber("Пять");
        operation.setSecondNumber("Шесть");

        dataBinder = new DataBinder(operation);
        dataBinder.addValidators(validator);
        dataBinder.validate(operation);
        errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(2, errorList.size());

        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        FieldError firstNumberError = (FieldError) errorList.get(0);
        Assert.assertEquals("firstNumber", firstNumberError.getField());
        Assert.assertEquals("value must be int", firstNumberError.getDefaultMessage());

        Assert.assertTrue(errorList.get(1) instanceof FieldError);
        FieldError secondNumberError = (FieldError) errorList.get(1);
        Assert.assertEquals("secondNumber", secondNumberError.getField());
        Assert.assertEquals("value must be int", secondNumberError.getDefaultMessage());
    }

    @Test
    public void mixErrorsValidatorTest(){
        // first value - null, second - empty
        operation.setFirstNumber(null);
        operation.setSecondNumber("");

        dataBinder.validate(operation);

        List<ObjectError> errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(2, errorList.size());

        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        FieldError firstNumberError = (FieldError) errorList.get(0);
        Assert.assertEquals("firstNumber", firstNumberError.getField());
        Assert.assertEquals("value can't be null", firstNumberError.getDefaultMessage());

        Assert.assertTrue(errorList.get(1) instanceof FieldError);
        FieldError secondNumberError = (FieldError) errorList.get(1);
        Assert.assertEquals("secondNumber", secondNumberError.getField());
        Assert.assertEquals("value can't be empty", secondNumberError.getDefaultMessage());

        // first value - empty, second - not int

        operation = new Operation();
        operation.setFirstNumber("");
        operation.setSecondNumber("Пять");

        dataBinder = new DataBinder(operation);
        dataBinder.addValidators(validator);
        dataBinder.validate(operation);
        errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(2, errorList.size());

        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        firstNumberError = (FieldError) errorList.get(0);
        Assert.assertEquals("firstNumber", firstNumberError.getField());
        Assert.assertEquals("value can't be empty", firstNumberError.getDefaultMessage());

        Assert.assertTrue(errorList.get(1) instanceof FieldError);
        secondNumberError = (FieldError) errorList.get(1);
        Assert.assertEquals("secondNumber", secondNumberError.getField());
        Assert.assertEquals("value must be int", secondNumberError.getDefaultMessage());

        // first value - not int, second - null

        operation = new Operation();
        operation.setFirstNumber("Пять");
        operation.setSecondNumber(null);

        dataBinder = new DataBinder(operation);
        dataBinder.addValidators(validator);
        dataBinder.validate(operation);
        errorList = dataBinder.getBindingResult().getAllErrors();

        Assert.assertEquals(2, errorList.size());

        Assert.assertTrue(errorList.get(0) instanceof FieldError);
        firstNumberError = (FieldError) errorList.get(0);
        Assert.assertEquals("firstNumber", firstNumberError.getField());
        Assert.assertEquals("value must be int", firstNumberError.getDefaultMessage());

        Assert.assertTrue(errorList.get(1) instanceof FieldError);
        secondNumberError = (FieldError) errorList.get(1);
        Assert.assertEquals("secondNumber", secondNumberError.getField());
        Assert.assertEquals("value can't be null", secondNumberError.getDefaultMessage());
    }


    @Test
    public void cacheTest() {
        printResult(service.add(5, 5));
        printResult(service.add(6, 6));
        printResult(service.add(5, 5));

        System.out.println("/////////////////////////////");

        printResult(service.divide(25, 5));
        printResult(service.divide(6, 2));
        printResult(service.divide(25, 5));

        System.out.println("/////////////////////////////");

        printResult(service.subtract(25, 5));
        printResult(service.subtract(6, 8));
        printResult(service.subtract(25, 5));

        System.out.println("/////////////////////////////");

        printResult(service.multiply(5, 5));
        printResult(service.multiply(6, 6));
        printResult(service.multiply(5, 5));
    }

    private void printResult(int result) {
        System.out.println("Result: " + result);
    }
}
