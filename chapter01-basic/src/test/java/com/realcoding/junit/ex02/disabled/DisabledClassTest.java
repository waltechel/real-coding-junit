package com.realcoding.junit.ex02.disabled;

import com.realcoding.junit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("기능이 아직 개발중")
public class DisabledClassTest {

    @Test
    public void testSqrt() {
        Calculator calculator = new Calculator();
        double ret = calculator.sqrt(16);
        Assertions.assertEquals(4, ret);
    }
}