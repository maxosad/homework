package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    public static final List<Pattern> patterns ;
    static {
        Pattern dateDividedMinus = Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2}$");
        Pattern dateDividedSlash = Pattern.compile("^\\d{1,2}/\\d{1,2}/(\\d{2}|\\d{4})$");
        Pattern yesterdayTodayTomorrow = Pattern.compile("^(yesterday|today|tommorow)$");
        Pattern ago = Pattern.compile("^(1 day|\\d+ days) ago$");
        patterns = List.of(dateDividedMinus, dateDividedSlash, yesterdayTodayTomorrow, ago);
    };

    public static Optional<LocalDate> parseDate(String string) {
        for (var pattern : patterns) {
            var matcher = pattern.matcher(string);
            if (matcher.find()) {
//                LocalDate.parse(string, matcher);
            }
        }
//        List<Pattern> hendlers = new ArrayList<>();
//        handlers.stream()
//            .filter(handler -> handler.canHandleRequest(req))
//            .findFirst()
        return Optional.empty();
    }

    //1 day ago
//2234 days ago
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-M-d");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-M-dd");
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyy-MM-d");
        DateTimeFormatter dateTimeFormatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2020-1-10", dateTimeFormatter2);
        System.out.println(localDate);
    }
}
