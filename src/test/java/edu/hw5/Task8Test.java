package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
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
            Arguments.of("0", true),
            Arguments.of("2", false)
        );
    }

    @ParameterizedTest
    @DisplayName("odd length")
    @MethodSource("provideOddLength")
    void oddLength(String string, boolean expected) {
        var result = Task8.oddLength(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideVolatileLength() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", true),
            Arguments.of("00111", true),
            Arguments.of("010", true),
            Arguments.of("101", false),
            Arguments.of("10", true),
            Arguments.of("1000", true),
            Arguments.of("0", true),
            Arguments.of("1", false),
            Arguments.of("2", false)
        );
    }

    @ParameterizedTest
    @DisplayName("volatile length")
    @MethodSource("provideVolatileLength")
    void volatileLength(String string, boolean expected) {
        var result = Task8.volatileLength(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideCountOfZerosIsMultipleOfThree() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", true),
            Arguments.of("00111", false),
            Arguments.of("010", false),
            Arguments.of("101", false),
            Arguments.of("10", false),
            Arguments.of("1000", true),
            Arguments.of("10000", false),
            Arguments.of("0", false),
            Arguments.of("1", false),
            Arguments.of("2", false),
            Arguments.of("220002", false)
        );
    }

    @ParameterizedTest
    @DisplayName("zeros count is multiple of three")
    @MethodSource("provideCountOfZerosIsMultipleOfThree")
    void zerosCountIsMultipleOfThree(String string, boolean expected) {
        var result = Task8.zerosCountIsMultipleOfThree(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideAnyBut11Or111() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", true),
            Arguments.of("00111", true),
            Arguments.of("010", true),
            Arguments.of("101", true),
            Arguments.of("10", true),
            Arguments.of("1000", true),
            Arguments.of("10000", true),
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("11", false),
            Arguments.of("111", false),
            Arguments.of("1111", true),
            Arguments.of("01111", true),
            Arguments.of("2", false),
            Arguments.of("220002", false)
        );
    }

    @ParameterizedTest
    @DisplayName("any but 11 or 111")
    @MethodSource("provideAnyBut11Or111")
    void anyBut11Or111(String string, boolean expected) {
        var result = Task8.anyBut11Or111(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideEveryOddLetterIsOne() {
        return Stream.of(
            Arguments.of("000", false),
            Arguments.of("00011", false),
            Arguments.of("00111", false),
            Arguments.of("010", false),
            Arguments.of("101", true),
            Arguments.of("10", true),
            Arguments.of("1000", false),
            Arguments.of("10000", false),
            Arguments.of("0", false),
            Arguments.of("1", true),
            Arguments.of("11", true),
            Arguments.of("111", true),
            Arguments.of("1111", true),
            Arguments.of("01111", false),
            Arguments.of("2", false),
            Arguments.of("220002", false)
        );
    }

    @ParameterizedTest
    @DisplayName("every odd letter is one")
    @MethodSource("provideEveryOddLetterIsOne")
    void everyOddLetterIsOne(String string, boolean expected) {
        var result = Task8.everyOddLetterIsOne(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideMoreOneZeroLessTwoOnes() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", false),
            Arguments.of("00111", false),
            Arguments.of("010", true),
            Arguments.of("101", false),
            Arguments.of("10", false),
            Arguments.of("1000", true),
            Arguments.of("10000", true),
            Arguments.of("0", false),
            Arguments.of("1", false),
            Arguments.of("11", false),
            Arguments.of("111", false),
            Arguments.of("1111", false),
            Arguments.of("01111", false),
            Arguments.of("2", false),
            Arguments.of("220002", false),
            Arguments.of("010", true),
            Arguments.of("010000", true),
            Arguments.of("0000010", true),
            Arguments.of("0000010000", true),
            Arguments.of("00010010000", false)
        );
    }

    @ParameterizedTest
    @DisplayName("more one zero less two ones")
    @MethodSource("provideMoreOneZeroLessTwoOnes")
    void moreOneZeroLessTwoOnes(String string, boolean expected) {
        var result = Task8.moreOneZeroLessTwoOnes(string);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideNoSequentialOne() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("00011", false),
            Arguments.of("00111", false),
            Arguments.of("010", true),
            Arguments.of("101", true),
            Arguments.of("10", true),
            Arguments.of("1000", true),
            Arguments.of("10000", true),
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("11", false),
            Arguments.of("111", false),
            Arguments.of("1111", false),
            Arguments.of("01111", false),
            Arguments.of("2", false),
            Arguments.of("220002", false),
            Arguments.of("010", true),
            Arguments.of("010000", true),
            Arguments.of("0000010", true),
            Arguments.of("0000010000", true),
            Arguments.of("00010010000", true),
            Arguments.of("000100110000", false)
        );
    }

    @ParameterizedTest
    @DisplayName("no sequential ones")
    @MethodSource("provideNoSequentialOne")
    void noSequentialOne(String string, boolean expected) {
        var result = Task8.noSequentialOne(string);

        assertEquals(expected, result);
    }


}
