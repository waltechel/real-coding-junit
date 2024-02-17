package com.realcoding.junit.ex06.dynamic;

import com.realcoding.junit.Calculator;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collection;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DynamicCalculatorTest {

    private Calculator calculator;

    @BeforeAll
    void setup(){
        calculator = new Calculator();
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsForCalculator() {
        return Arrays.asList(
                DynamicTest.dynamicTest("더하기 테스트", () -> Assertions.assertEquals(5, calculator.add(2, 3))),
                DynamicTest.dynamicTest("빼기 테스트", () -> Assertions.assertEquals(1, calculator.subtract(3, 2))),
                DynamicTest.dynamicTest("곱하기 테스트", () -> Assertions.assertEquals(6, calculator.multiply(2, 3))),
                DynamicTest.dynamicTest("나누기 테스트", () -> Assertions.assertEquals(2, calculator.divide(4, 2))),
                DynamicTest.dynamicTest("나누기 예외 테스트", () -> Assertions.assertThrows(RuntimeException.class, () -> calculator.divide(4, 0)))
        );
    }
}
