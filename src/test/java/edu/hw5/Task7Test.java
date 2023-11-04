package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task7Test
{

    private static Stream<Arguments> provideThirdZero() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", true),
            Arguments.of("00111", false),
            Arguments.of("0", false),
            Arguments.of("2", false)
        );
    }

    @ParameterizedTest
    @DisplayName("third zero test")
    @MethodSource("provideThirdZero")
    void thirdZero(String string, boolean expected) {
        var result = Task7.thirdZero(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideStartsEndsSame() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", false),
            Arguments.of("00111", false),
            Arguments.of("010", true),
            Arguments.of("101", true),
            Arguments.of("2", false)
        );
    }

    @ParameterizedTest
    @DisplayName("start end same")
    @MethodSource("provideStartsEndsSame")
    void startsEndsSame(String string, boolean expected) {
        var result = Task7.startsEndsSame(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideSizeOneToThree() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", false),
            Arguments.of("00111", false),
            Arguments.of("010", true),
            Arguments.of("101", true),
            Arguments.of("10", true),
            Arguments.of("1", true),
            Arguments.of("2", false)
        );
    }

    @ParameterizedTest
    @DisplayName("size from one to three")
    @MethodSource("provideSizeOneToThree")
    void sizeOneToThree(String string, boolean expected) {
        var result = Task7.sizeOneToThree(string);

        assertEquals(expected, result);
    }
}
