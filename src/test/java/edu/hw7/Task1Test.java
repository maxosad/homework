package edu.hw7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    private static Stream<Arguments> provideNumberOfIncrements() {
        return Stream.of(
            Arguments.of(10, 20),
            Arguments.of(300, 600),
            Arguments.of(1000, 2000)
        );
    }
    @ParameterizedTest
    @MethodSource("provideNumberOfIncrements")
    void parallelIncrement(int input, int expected) {
        Task1 task1 = new Task1();

        var res =  task1.parallelIncrement(input);

        assertEquals(expected, res);
    }
}
