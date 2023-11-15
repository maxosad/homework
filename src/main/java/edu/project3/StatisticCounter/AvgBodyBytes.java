package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import java.util.List;

public class AvgBodyBytes implements StatisticCounter<Double> {
    @Override
    public Double countStatistic(List<LogRecord> logRecords) {
        return logRecords.stream()
            .mapToInt(LogRecord::bodyBytes)
            .average().orElse(0);
    }
}
