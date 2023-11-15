package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

public class LateDate implements StatisticCounter<OffsetDateTime> {
    @Override
    public OffsetDateTime countStatistic(List<LogRecord> logRecords) {
        return logRecords.stream()
            .map(LogRecord::date)
            .max(Comparator.naturalOrder()).orElse(null);
    }
}
