package com.jyujyu_dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LombokTestDataTest {
    @Test
    public void testDataTest() throws Exception {
        // given
        TestData testData = new TestData();
        testData.setName("koo");
        // when

        // then
        Assertions.assertEquals("koo", testData.getName());
    }
}
