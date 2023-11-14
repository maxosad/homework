package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    private static Stream<Arguments> provideYearToCountFriday() {
        return Stream.of(
            Arguments.of(List.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)), 2024),
            Arguments.of(List.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 3, 13),
                LocalDate.of(1925, 11, 13)), 1925)
        );
    }


    @ParameterizedTest
    @DisplayName("example tests")
    @MethodSource("provideYearToCountFriday")
    void findFridayThirteenth(List<LocalDate> expected, int year) {
        assertEquals(expected, Task2.findFridayThirteenth(year));
    }

    private static Stream<Arguments> provideLocalDateToFindNextFridayThirteenth() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)),
            Arguments.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 3, 13))
        );
    }

    @ParameterizedTest
    @DisplayName("example tests")
    @MethodSource("provideLocalDateToFindNextFridayThirteenth")
    void nextFriday(LocalDate input, LocalDate expected) {
        var result = Task2.nextFriday(input);

        assertEquals(expected, result);
    }
}
