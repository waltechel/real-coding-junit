package com.realcoding.junit.ex05.parameterized;

import com.realcoding.junit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.StringTokenizer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterizedWithValueSourceTest {

    private Calculator calculator;

    @BeforeAll
    void setUp(){
        calculator = new Calculator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"3 3 6", "5 5 10", "10 15 25"})
    void testAdd1(String sentence){
        StringTokenizer st = new StringTokenizer(sentence);
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int n3 = Integer.parseInt(st.nextToken());
        Assertions.assertEquals(calculator.add(n1, n2), n3);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calculator_add_source.csv")
    void testAdd2(int n1, int n2, int expectedResult) {
        Assertions.assertEquals(expectedResult, calculator.add(n1, n2));
    }
}
