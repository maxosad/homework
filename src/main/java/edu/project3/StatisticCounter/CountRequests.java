package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import java.util.List;

public class CountRequests implements StatisticCounter<Integer> {
    @Override
    public Integer countStatistic(List<LogRecord> logRecords) {
        return logRecords.size();
    }
}
