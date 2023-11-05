package edu.hw5.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    private static Stream<Arguments> provideDate() {
        return Stream.of(
            Arguments.of("2020-10-10", Optional.of(LocalDate.parse("2020-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.parse("2020-12-2", DateTimeFormatter.ofPattern("yyyy-MM-d")))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.parse("1/3/1976", DateTimeFormatter.ofPattern("d/M/yyyy")))),
            Arguments.of("1/3/20", Optional.of(LocalDate.parse("1/3/20", DateTimeFormatter.ofPattern("d/M/yy")))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234)))
        );
    }
//2020-10-10
//    2020-12-2
//    1/3/1976
//    1/3/20
//    tomorrow
//        today
//    yesterday
//1 day ago
//2234 days ago
    @ParameterizedTest
    @DisplayName("example date test")
    @MethodSource("provideDate")
    void parseDate(String date, Optional<LocalDate> expected) {
        var result = Task3.parseDate(date);

        assertEquals(expected, result);
    }
}
