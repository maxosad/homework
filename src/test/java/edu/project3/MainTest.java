package edu.project3;

import edu.project3.Parser.ParserImpl;
import edu.project3.StatisticCounter.StatisticCounter;
import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    public static final String dir = "src/test/java/edu/project3/testData";

    @Test
    void parserTest() {
        List<LogRecord> expected = List.of(
            new LogRecord(
                "7.26.93.214",
                "-",
                OffsetDateTime.parse("2015-05-17T08:05:52Z"),
                "GET",
                "/downloads/product_2",
                "200",
                3318,
                "\"-\"",
                "Go1.1packagehttp"
            ),
            new LogRecord(
                "3.23.226.37",
                "-",
                OffsetDateTime.parse("2015-05-17T08:05:19Z"),
                "GET",
                "/downloads/product_2",
                "200",
                2578,
                "\"-\"",
                "urlgrabber/3.9.1yum/3.4.3"
            )
        );
        ParserImpl parser = new ParserImpl();

        var res = parser.parse(dir);

        assertEquals(expected, res);
    }

    @ParameterizedTest
    <T> void statisticTest(Statistic<T> expected, StatisticCounter<T> statisticCounter, List<LogRecord> logRecords) {

        var res = statisticCounter.countStatistic(logRecords);

        assertEquals(expected, res);
    }

}
