package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateLikePatternHandler extends AbstractHandler {
    private String format;

    public DateLikePatternHandler(String pattern, String format) {
        this.pattern = pattern;
        this.format = format;
    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (string.matches(pattern)) {
            return Optional.of(LocalDate.parse(string, DateTimeFormatter.ofPattern(format)));
        }
        return Optional.empty();
    }
}
