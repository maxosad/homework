package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static final int THIRTEENTH = 13;
    public static final int MONTH_IN_YEAR = 12;

    private Task2() { }

    public static LocalDate[] findFridayThirteenth(int year) {
        List<LocalDate> list = new ArrayList<>(MONTH_IN_YEAR);

        for (int month = 1; month <= MONTH_IN_YEAR; month++) {
            LocalDate localDate = LocalDate.of(year, month, THIRTEENTH);
            if (localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                list.add(localDate);
            }
        }

        var answer = list.toArray(new LocalDate[]{});
        return answer;
    }

    public static LocalDate nextFriday(LocalDate localDate) {
        LocalDate localDateCopy = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (localDateCopy.getDayOfMonth() != THIRTEENTH) {
            localDateCopy = localDateCopy.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return localDateCopy;
    }

}
