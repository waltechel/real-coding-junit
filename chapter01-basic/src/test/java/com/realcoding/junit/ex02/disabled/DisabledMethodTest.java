package com.realcoding.junit.ex02.disabled;

import com.realcoding.junit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledMethodTest {

    @Test
    @Disabled
    public void sqrt1() {
        Calculator calculator = new Calculator();
        double ret = calculator.sqrt(16);
        Assertions.assertEquals(4, ret);
    }

    @Test
    @Disabled("기능이 아직 개발중")
    public void sqrt2() {
        Calculator calculator = new Calculator();
        double ret = calculator.sqrt(16);
        Assertions.assertEquals(4, ret);
    }
}