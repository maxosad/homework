package edu.hw5.task3;

import edu.hw5.task3.handlers.AbstractHandler;
import edu.hw5.task3.handlers.DateLikePatternHandler;
import edu.hw5.task3.handlers.DaysAgoHandler;
import edu.hw5.task3.handlers.WordDateHandler;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Task3 {

    private Task3() { }

    public static final List<AbstractHandler> HANDLERS;

    static {
        AbstractHandler minusSep1 = new DateLikePatternHandler("\\d{4}-\\d-\\d", "yyyy-M-d");
        AbstractHandler minusSep2 = new DateLikePatternHandler("\\d{4}-\\d-\\d{2}", "yyyy-M-dd");
        AbstractHandler minusSep3 = new DateLikePatternHandler("\\d{4}-\\d{2}-\\d", "yyyy-MM-d");
        AbstractHandler minusSep4 = new DateLikePatternHandler("\\d{4}-\\d{2}-\\d{2}", "yyyy-MM-dd");

        AbstractHandler slashSep1 = new DateLikePatternHandler("\\d/\\d/\\d{4}", "d/M/yyyy");
        AbstractHandler slashSep2 = new DateLikePatternHandler("\\d{2}/\\d/\\d{4}", "dd/M/yyyy");
        AbstractHandler slashSep3 = new DateLikePatternHandler("\\d{2}/\\d{2}/\\d{4}", "dd/MM/yyyy");
        AbstractHandler slashSep4 = new DateLikePatternHandler("\\d/\\d{2}/\\d{4}", "d/MM/yyyy");
        AbstractHandler slashSep5 = new DateLikePatternHandler("\\d/\\d/\\d{2}", "d/M/yy");

        AbstractHandler wordDateHandler = new WordDateHandler();

        AbstractHandler daysAgoHandler = new DaysAgoHandler("(1 day|\\d+ days) ago");

        HANDLERS = List.of(minusSep1, minusSep2, minusSep3, minusSep4,
            slashSep1, slashSep2, slashSep3, slashSep4, slashSep5,
            wordDateHandler,
            daysAgoHandler);

    }

    public static Optional<LocalDate> parseDate(String string) {
        for (AbstractHandler handler : HANDLERS) {
            var result = handler.parseDate(string);
            if (!result.equals(Optional.empty())) {
                return result;
            }
        }
        return Optional.empty();
    }

}
