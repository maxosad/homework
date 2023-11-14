package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import java.util.List;
import java.util.Map;

public interface StatisticCounter<T> {
    T countStatistic (List<LogRecord> logRecords);
}
