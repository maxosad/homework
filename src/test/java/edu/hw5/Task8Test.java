package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task8Test {

    private static Stream<Arguments> provideOddLength() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", true),
            Arguments.of("00111", true),
            Arguments.of("010", true),
            Arguments.of("101", true),
            Arguments.of("10", false),
            Arguments.of("1000", false),
            Arguments.of("1", true),
            Arguments.of("2", false)
        );
    }

    @ParameterizedTest
    @DisplayName("size from one to three")
    @MethodSource("provideOddLength")
    void oddLength(String string, boolean expected) {
        var result = Task8.oddLength(string);

        assertEquals(expected, result);
    }
}
