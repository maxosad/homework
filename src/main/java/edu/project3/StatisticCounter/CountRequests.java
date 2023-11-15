package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.List;

public class CountRequests implements StatisticCounter<Integer> {
    public static final String title = "CountRequests";
    @Override
    public Statistic<Integer> countStatistic(List<LogRecord> logRecords) {
        return new Statistic<>(title, logRecords.size());
    }
}
