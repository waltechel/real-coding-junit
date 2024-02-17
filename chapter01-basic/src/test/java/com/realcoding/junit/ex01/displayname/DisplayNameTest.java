package com.realcoding.junit.ex01.displayname;

import com.realcoding.junit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("계산기 테스트")
public class DisplayNameTest {


    @Test
    @DisplayName("덧셈 테스트 😊")
    public void testAdd() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(5, calculator.add(2, 3), "2와 3의 덧셈이 올바르게 동작해야 합니다.");
    }

    @Test
    @DisplayName("뺄셈 테스트 😢")
    public void testSubtract() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(1, calculator.subtract(3, 2), "3에서 2를 뺀 결과가 올바르게 동작해야 합니다.");
    }

    @Test
    @DisplayName("곱셈 테스트 🤗")
    public void testMultiply() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(6, calculator.multiply(2, 3), "2와 3의 곱셈이 올바르게 동작해야 합니다.");
    }

    @Test
    @DisplayName("나눗셈 테스트 🤔")
    public void testDivide() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(2, calculator.divide(6, 3), "6을 3으로 나눈 결과가 올바르게 동작해야 합니다.");
        Assertions.assertEquals(16.6, calculator.divide(50, 3), 0.1, "50을 3으로 나눈 결과가 올바르게 동작해야 합니다.");

    }

    // sqrt 메서드에 대한 테스트는 메서드가 완성되면 작성하세요.
}
