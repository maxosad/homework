package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.List;

public class AvgBodyBytes implements StatisticCounter<Double> {
    public static final String title = "AvgBodyBytes";
    @Override
    public Statistic<Double> countStatistic(List<LogRecord> logRecords) {
        return new Statistic<>(title, logRecords.stream()
            .mapToInt(LogRecord::bodyBytes)
            .average().orElse(0));
    }
}
