package edu.hw3.Task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

//    convertToRoman(2) ➞ "II"
//    convertToRoman(12) ➞ "XII"
//    convertToRoman(16) ➞ "XVI"
private static Stream<Arguments> provideIntsForConvert() {
    return Stream.of(
        Arguments.of("II", 2),
        Arguments.of("XII", 12),
        Arguments.of("XVI", 16),
        Arguments.of("IV", 4)
    );
}

    @ParameterizedTest
    @DisplayName("example tests")
    @MethodSource("provideIntsForConvert")
    void convertToRoman1(String expected, int intToConvert) {
        Assertions.assertEquals(expected, Task4.convertToRoman(intToConvert));
    }

    @Test
    @DisplayName("Illegal argument")
    void illegalTest() {
        var thr = assertThrows(IllegalArgumentException.class,
            () -> Task4.convertToRoman(0));
        assertEquals("number should be more zero and less four thousands",
            thr.getMessage());
    }

}
