package com.realcoding.junit.ex04.assertions;

import com.realcoding.junit.Calculator;
import org.junit.jupiter.api.*;

@DisplayName("Assertions 를 사용한 테스트")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssertionsTest {

    private Calculator calculator;

    @BeforeAll
    void beforeAll(){
        calculator = new Calculator();
    }

    @Test
    @DisplayName("다 틀리는 더하기 테스트")
    void add1(){
        Assertions.assertEquals(1234, calculator.add(50, 10));
        Assertions.assertEquals(1234, calculator.add(50, 20));
        Assertions.assertEquals(1234, calculator.add(50, 30));
    }

    @Test
    @DisplayName("assertAll 을 사용한 다 틀리는 더하기 테스트")
    void add2(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(1234, calculator.add(50, 10)),
                () -> Assertions.assertEquals(1234, calculator.add(50, 20)),
                () -> Assertions.assertEquals(1234, calculator.add(50, 30))
        );
    }

    @Test
    @DisplayName("예외 테스트")
    void divide(){
        Throwable throwable = Assertions.assertThrows(RuntimeException.class, () -> {
            calculator.divide(10, 0);
        });
        Assertions.assertEquals("cannot be divided by zero.", throwable.getMessage());
    }
}
