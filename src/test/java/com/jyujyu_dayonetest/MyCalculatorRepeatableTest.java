package com.jyujyu_dayonetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MyCalculatorRepeatableTest {

    @DisplayName("단순 덧셈 반복 테스트")
    @RepeatedTest(5)
    public void repeatedAddTest() {
        // given
        MyCalculator myCalculator = new MyCalculator();

        // when
        myCalculator.add(10.0);

        // then
        assertEquals(10.0, myCalculator.getResult());
    }

    @DisplayName("덧셈을 5번 반복해서 테스트")
    @ParameterizedTest
    @MethodSource("parameterizedTestParameters")
    public void parameterizedTest(Double addValue, Double expectValue) {
        // given
        MyCalculator myCalculator = new MyCalculator();

        // when
        myCalculator.add(addValue);

        // then
        assertEquals(expectValue, myCalculator.getResult());
    }

    public static Stream<Arguments> parameterizedTestParameters() {
        return Stream.of(
            Arguments.of(10.0, 10.0),
            Arguments.of(8.0, 8.0),
            Arguments.of(4.0, 4.0),
            Arguments.of(16.0, 16.0),
            Arguments.of(13.0, 13.0)
        );
    }

    @DisplayName("파라미터를 넣으며 사칙연산을 반복 테스트")
    @ParameterizedTest
    @MethodSource("parameterizedComplicatedCalculatedTestParameters")
    public void parameterizedComplicatedCalculatedTest(
        Double addValue,
        Double minusValue,
        Double multiplyValue,
        Double divideValue,
        Double expectValue) {
        // given
        MyCalculator myCalculator = new MyCalculator(0.0);

        // when
        Double result = myCalculator
            .add(addValue)
            .minus(minusValue)
            .multiply(multiplyValue)
            .divide(divideValue)
            .getResult();

        // then
        assertEquals(expectValue, result);
    }

    public static Stream<Arguments> parameterizedComplicatedCalculatedTestParameters() {
        return Stream.of(
            Arguments.of(10.0, 4.0, 2.0, 3.0, 4.0),
            Arguments.of(4.0, 2.0, 4.0, 2.0, 4.0)
        );
    }
}
