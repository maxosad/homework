package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class LinearMonteCarloTest {

    private static Stream<Arguments> providePassword() {
        return Stream.of(
            Arguments.of(
                "qwertyio",
                false),
            Arguments.of(
                "%",
                true
            )
        );
    }

    @ParameterizedTest
    @DisplayName("example tests")
    @MethodSource("providePassword")
    void validatePass(String pass, boolean expected) {
        var result = Task4.validatePass(pass);

        assertEquals(expected, result);
    }
}
