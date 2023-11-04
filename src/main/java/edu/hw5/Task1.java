package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Task1 {

    private Task1() { }

    public static final int MINUTES_IN_HOUR = 60;

    public static String countAvg(List<String> dates) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm");
        Duration durationSum = Duration.ofDays(0);

        int count = 0;
        for (var d : dates) {
            var strings = d.split(" - ");

            Date date1 = null;
            Date date2 = null;
            try {
                date1 = sdf.parse(strings[0]);
                date2 = sdf.parse(strings[1]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Instant instant1 = date1.toInstant();
            Instant instant2 = date2.toInstant();

            Duration duration = Duration.between(instant1, instant2);
            durationSum = durationSum.plus(duration);
            count++;
        }

        Duration durationAvg = durationSum.dividedBy(count);
        var hours = durationAvg.toHours();
        var minutes = durationAvg.toMinutes() - durationAvg.toHours() * MINUTES_IN_HOUR;

        return "%dч %dм".formatted(hours, minutes);
    }
}
