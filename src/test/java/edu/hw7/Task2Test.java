package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    private static Stream<Arguments> provideInts() {
        return Stream.of(
            Arguments.of(720, 6),
            Arguments.of(3628800, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInts")
    void fact(int expected, int input) {
        var res = Task2.fact(input);

        assertEquals(expected, res);
    }
}
