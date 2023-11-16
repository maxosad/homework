package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.List;

public class AvgBodyBytes implements StatisticCounter<Double> {
    public static final String title = "AvgBodyBytes";
    public static final String keyName = "AvgBodyBytes";
    public static final String valueName = "Value";
    @Override
    public Statistic<Double> countStatistic(List<LogRecord> logRecords) {
        return new Statistic<>(title, keyName, valueName, logRecords.stream()
            .mapToInt(LogRecord::bodyBytes)
            .average().orElse(0));
    }
}
