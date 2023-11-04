package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task5Test {

    private static Stream<Arguments> provideNumber() {
        return Stream.of(
            Arguments.of(
                "А123ВЕ777",
                true),
            Arguments.of(
                "О777ОО177",
                true
            ),
            Arguments.of(
                "123АВЕ777",
                false
            ),
            Arguments.of(
                "А123ВГ77",
                false
            ),
            Arguments.of(
                "А123ВЕ7777",
                false
            )
        );
    }

    @ParameterizedTest
    @DisplayName("example tests")
    @MethodSource("provideNumber")
    void validateNumber(String number, boolean expected) {
        var result = Task5.validateNumber(number);

        assertEquals(expected, result);
    }
}
