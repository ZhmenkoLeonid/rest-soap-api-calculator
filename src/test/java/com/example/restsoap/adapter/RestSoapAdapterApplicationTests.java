package com.example.restsoap.adapter;

import com.example.restsoap.adapter.service.CalculatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestSoapAdapterApplicationTests {
    @Autowired
    @Qualifier("testSOAPCalculatorService")
    private CalculatorService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void normalAddTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("http://localhost/api/calculator/add?firstNumber={1}&secondNumber={2}", 5, 5))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(Integer.parseInt(result.getResponse().getContentAsString()), 10);
    }

    @Test
    public void normalSubtractTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("http://localhost/api/calculator/subtract?firstNumber={1}&secondNumber={2}", 10,2))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(Integer.parseInt(result.getResponse().getContentAsString()), 8);
    }

    @Test
    public void normalDivideTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("http://localhost/api/calculator/divide?firstNumber={1}&secondNumber={2}", 30,5))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(Integer.parseInt(result.getResponse().getContentAsString()), 6);
    }

    @Test
    public void normalMultiplyTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("http://localhost/api/calculator/multiply?firstNumber={1}&secondNumber={2}", 4,4))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(Integer.parseInt(result.getResponse().getContentAsString()), 16);
    }

    @Test
    public void nullValueAddTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("http://localhost/api/calculator/add?secondNumber={2}", 4))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "BAD REQUEST: firstNumber : value can't be null");

        result = mockMvc.perform(
                get("http://localhost/api/calculator/add"))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),
                "BAD REQUEST: firstNumber : value can't be null\nsecondNumber : value can't be null");
    }

    @Test
    public void nullValueSubtractTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("http://localhost/api/calculator/subtract?secondNumber={2}", 4))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "BAD REQUEST: firstNumber : value can't be null");

        result = mockMvc.perform(
                get("http://localhost/api/calculator/subtract"))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),
                "BAD REQUEST: firstNumber : value can't be null\nsecondNumber : value can't be null");
    }

    @Test
    public void nullValueDivideTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("http://localhost/api/calculator/divide?secondNumber={2}", 4))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "BAD REQUEST: firstNumber : value can't be null");

        result = mockMvc.perform(
                get("http://localhost/api/calculator/divide"))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),
                "BAD REQUEST: firstNumber : value can't be null\nsecondNumber : value can't be null");
    }

    @Test
    public void nullValueMultiplyTest() throws Exception {
        MvcResult result = mockMvc.perform(
                get("http://localhost/api/calculator/multiply?secondNumber={2}", 4))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "BAD REQUEST: firstNumber : value can't be null");

        result = mockMvc.perform(
                get("http://localhost/api/calculator/multiply"))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),
                "BAD REQUEST: firstNumber : value can't be null\nsecondNumber : value can't be null");
    }


    @Test
    public void cacheTest() {
        System.out.println("///////START CACHE TEST///////");
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
        System.out.println("////////END CACHE TEST////////");
    }

    private void printResult(int result) {
        System.out.println("Result: " + result);
    }
}