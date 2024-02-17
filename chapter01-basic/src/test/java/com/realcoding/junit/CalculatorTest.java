package com.realcoding.junit;

import org.junit.jupiter.api.Test;                      // JUnit 5를 사용하고 있으므로 org.junit.jupiter 패키지의 Test 사용

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {                                  // 테스트 클래스에는 Test 접미사를 붙이는 것이 일반적인 관행

    @Test                                               // @Test 어노테이션을 붙이면 JUnit은 해당 메서드를 테스트로 인식
    void add() {                                        // JUnit 5에서는 테스트 메서드가 public 일 필요가 없다
        Calculator calculator = new Calculator();
        double result = calculator.add(50, 10);
        assertEquals(60, result, 0);       // 단언문을 사용하여 결괏값 검증, delta는 부동 소수점 오류를 계산할 때 사용
    }

    @Test
    void subtract() {                                   // JUnit 5에서는 테스트 메서드 앞에 test 라는 접두사를 붙일 필요가 없다
        Calculator calculator = new Calculator();
        double result = calculator.subtract(50, 10);
        assertEquals(40, result, 0);
    }

    @Test
    void multiply() {                                   // JUnit 5에서는 테스트 메서드 앞에 test 라는 접두사를 붙일 필요가 없다
        Calculator calculator = new Calculator();
        double result = calculator.multiply(50, 10);
        assertEquals(500, result, 0);
    }

    @Test
    void divide() {
        Calculator calculator = new Calculator();
        double result = calculator.divide(50, 3);
        assertEquals(16.6, result, 0.1);  // 단언문을 사용하여 결괏값 검증, delta는 부동 소수점 오류를 계산할 때 사용
    }
}