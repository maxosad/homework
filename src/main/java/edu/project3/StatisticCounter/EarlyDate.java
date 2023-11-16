package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

public class EarlyDate implements StatisticCounter<OffsetDateTime> {
    public static final String title = "EarlyDate";
    public static final String keyName = "EarlyDate";
    public static final String valueName = "Quantity";
    @Override
    public Statistic<OffsetDateTime> countStatistic(List<LogRecord> logRecords) {
        return new Statistic<>(title, keyName, valueName, logRecords.stream()
            .map(LogRecord::date)
            .min(Comparator.naturalOrder()).orElse(null));
    }
}
