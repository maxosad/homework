package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

public class LateDate implements StatisticCounter<OffsetDateTime> {
    public static final String title = "LateDate";
    @Override
    public Statistic<OffsetDateTime> countStatistic(List<LogRecord> logRecords) {
        return new Statistic<>(title, logRecords.stream()
            .map(LogRecord::date)
            .max(Comparator.naturalOrder()).orElse(null));
    }
}
