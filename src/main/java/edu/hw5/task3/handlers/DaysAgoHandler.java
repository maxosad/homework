package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAgoHandler extends AbstractHandler {

    public DaysAgoHandler(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (string.matches(pattern)) {
            var numberString = string.split(" ")[0];
            int number = Integer.parseInt(numberString);
            return Optional.of(LocalDate.now().minusDays(number));
        }
        return Optional.empty();
    }
}
