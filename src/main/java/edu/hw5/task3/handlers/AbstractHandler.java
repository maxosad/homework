package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public abstract class AbstractHandler {
    String pattern;

    public abstract Optional<LocalDate> parseDate(String string);
}
