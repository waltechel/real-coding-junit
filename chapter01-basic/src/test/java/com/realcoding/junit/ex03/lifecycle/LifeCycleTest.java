package com.realcoding.junit.ex03.lifecycle;

import com.realcoding.junit.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("라이프사이클을 적용한 테스트")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LifeCycleTest {

    private Calculator calculator;

    @BeforeAll
    void setup() {
        calculator = new Calculator();
        System.out.println("@BeforeAll test");
    }

    @BeforeEach
    void init() {
        System.out.println("@BeforeEach test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach test");
    }

    @AfterAll
    void done() {
        System.out.println("@AfterAll test");
    }

    @Test
    @DisplayName("덧셈 테스트 😊")
    public void testAdd() {
        System.out.println("@DisplayName(\"덧셈 테스트 \uD83D\uDE0A\")");
        Assertions.assertEquals(5, calculator.add(2, 3), "2와 3의 덧셈이 올바르게 동작해야 합니다.");
    }

    @Test
    @DisplayName("뺄셈 테스트 😢")
    public void testSubtract() {
        System.out.println("@DisplayName(\"뺄셈 테스트 \uD83D\uDE22\")");
        Assertions.assertEquals(1, calculator.subtract(3, 2), "3에서 2를 뺀 결과가 올바르게 동작해야 합니다.");
    }

    @Test
    @DisplayName("곱셈 테스트 🤗")
    public void testMultiply() {
        System.out.println("@DisplayName(\"곱셈 테스트 \uD83E\uDD17\")");
        Assertions.assertEquals(6, calculator.multiply(2, 3), "2와 3의 곱셈이 올바르게 동작해야 합니다.");
    }

    @Test
    @DisplayName("나눗셈 테스트 🤔")
    public void testDivide() {
        System.out.println("@DisplayName(\"나눗셈 테스트 \uD83E\uDD14\")");
        Assertions.assertEquals(2, calculator.divide(6, 3), "6을 3으로 나눈 결과가 올바르게 동작해야 합니다.");
        Assertions.assertEquals(16.6, calculator.divide(50, 3), 0.1, "50을 3으로 나눈 결과가 올바르게 동작해야 합니다.");
    }
}
