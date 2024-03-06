package com.jyujyu_dayonetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JunitPracticeTest {

    @Test
    public void assertEqualsTest() throws Exception {
        // given
        String expect = "Something";
        String actual = "Something";
        // when & then
        assertEquals(expect, actual);

    }

    @Test
    public void assertNotEqualsTest() throws Exception {
        // given
        String expect = "Something";
        String actual = "Hello";
        // when & then
        assertNotEquals(expect, actual);
    }

    @Test
    public void assertTrueTest() throws Exception {
        // given
        Integer a = 10;
        Integer b = 10;
        // when & then
        assertTrue(a.equals(b));
    }

    @Test
    public void assertFalseTest() throws Exception {
        // given
        Integer a = 10;
        Integer b = 11;
        // when & then
        assertFalse(a.equals(b));
    }

    @Test
    public void assertThrowsTest() throws Exception {
        // given & when & then
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의로 발생시킨 에러");
        });
    }

    @Test
    public void assertNotNullTest() throws Exception {
        // given
        String value = "Hello";
        // when & then
        assertNotNull(value);
    }

    @Test
    public void assertNullTest() throws Exception {
        // given
        String value = null;
        // when & then
        assertNull(value);
    }

    @Test
    public void assertIterableEquals() throws Exception {
        // given
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);
        // when & then
        Assertions.assertIterableEquals(list1, list2);
    }

    @Test
    public void assertAllTest() throws Exception {
        // given
        String expect = "Something";
        String actual = "Something";

        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        assertAll("Assert All", List.of(
            () -> { assertEquals(expect, actual); },
            () -> { Assertions.assertIterableEquals(list1, list2); }
        ));
    }
}
