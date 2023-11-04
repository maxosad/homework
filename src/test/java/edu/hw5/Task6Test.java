package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    private static Stream<Arguments> provideNumber() {
        return Stream.of(
            Arguments.of(
                "abc",
                "achfdbaabgabcaabg", true)
        );
    }

    @ParameterizedTest
    @DisplayName("example tests")
    @MethodSource("provideNumber")
    void isSub(String pattern, String string, boolean expected) {
        var result = Task6.isSub(pattern, string);

        assertEquals(expected, result);
    }
}
