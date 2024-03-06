package com.jyujyu_dayonetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyCalculatorTest {

    @Test
    @DisplayName("calculator add test")
    public void addTest() throws Exception {
        // given
        MyCalculator myCalculator = new MyCalculator();

        // when
        myCalculator.add(10.0);

        // then
        assertEquals(10.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("calculator subtract test")
    public void minusTest() throws Exception {
        // given
        MyCalculator myCalculator = new MyCalculator(10.0);

        // when
        myCalculator.minus(5.0);

        // then
        assertEquals(5.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("calculator multiply test")
    public void multiplyTest() throws Exception {
        // given
        MyCalculator myCalculator = new MyCalculator(2.0);

        // when
        myCalculator.multiply(2.0);

        // then
        assertEquals(4.0, myCalculator.getResult());
    }

    @Test
    public void divideTest() throws Exception {
        // given
        MyCalculator myCalculator = new MyCalculator(10.0);

        // when
        myCalculator.divide(2.0);

        // then
        assertEquals(5.0, myCalculator.getResult());
    }
    
    @Test
    public void divideZero() throws Exception {
        // given
        MyCalculator myCalculator = new MyCalculator(10.0);

        // when & then
        assertThrows(MyCalculator.ZeroDivisionException.class, () -> {
            myCalculator.divide(0.0);
        });
    }
}