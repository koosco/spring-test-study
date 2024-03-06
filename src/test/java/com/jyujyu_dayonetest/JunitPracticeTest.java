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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JunitPracticeTest {

    @Test
    @DisplayName("assert equal test")
    public void assert_equals_test() throws Exception {
        // given
        String expect = "Something";
        String actual = "Something";
        // when & then
        assertEquals(expect, actual);

    }

    @Test
    @DisplayName("assert not equal test")
    public void assertNotEqualsTest() throws Exception {
        // given
        String expect = "Something";
        String actual = "Hello";
        // when & then
        assertNotEquals(expect, actual);
    }

    @Test
    @DisplayName("assert true test")
    public void assertTrueTest() throws Exception {
        // given
        Integer a = 10;
        Integer b = 10;
        // when & then
        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("assert false test")
    public void assertFalseTest() throws Exception {
        // given
        Integer a = 10;
        Integer b = 11;
        // when & then
        assertFalse(a.equals(b));
    }

    @Test
    @DisplayName("assert throw test")
    public void assertThrowsTest() throws Exception {
        // given & when & then
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의로 발생시킨 에러");
        });
    }

    @Test
    @DisplayName("assert not null test")
    public void assertNotNullTest() throws Exception {
        // given
        String value = "Hello";
        // when & then
        assertNotNull(value);
    }

    @Test
    @DisplayName("assert null test")
    public void assertNullTest() throws Exception {
        // given
        String value = null;
        // when & then
        assertNull(value);
    }

    @Test
    @DisplayName("assert iterable test")
    public void assertIterableEquals() throws Exception {
        // given
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);
        // when & then
        Assertions.assertIterableEquals(list1, list2);
    }

    @Test
    @DisplayName("assert all test")
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
