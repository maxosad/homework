package edu.project3.util;

import edu.project3.Printer.MarkdownPrinter;
import edu.project3.Printer.Printer;
import edu.project3.model.Statistic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    public static final String QWER = "qwer";

    //    System.out.println(rightAlignmentFixSize("qwer","*", 4));
//        System.out.println(middleAlignmentFixSize("qwer","*", 5));
private static Stream<Arguments> provideStringsForMiddleAlignmentTest() {
    return Stream.of(
        Arguments.of(
            "**qwer**",
            QWER
        ),
        Arguments.of(
            "*qwer",
            QWER
        )
    );
}


    @ParameterizedTest
    @DisplayName("middleAlignmentFixSizeTest")
    @MethodSource("provideStringsForMiddleAlignmentTest")
    void middleAlignmentFixSize(String expected, String input) {
        var res =  Util.middleAlignmentFixSize(input,"*", expected.length());

        assertEquals(expected, res);
    }

    private static Stream<Arguments> provideStringsForRightAlignmentFixSizeTest() {
        return Stream.of(
            Arguments.of(
                "****qwer",
                QWER
            ),
            Arguments.of(
                "*qwer",
                QWER
            )
        );
    }

    @ParameterizedTest
    @DisplayName("rightAlignmentFixSizeTest")
    @MethodSource("provideStringsForRightAlignmentFixSizeTest")
    void rightAlignmentFixSize(String expected, String input) {
        var res =  Util.rightAlignmentFixSize(input,"*", expected.length());

        assertEquals(expected, res);
    }
}
