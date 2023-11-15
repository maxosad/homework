package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentCode implements StatisticCounter<Map<String, Integer>>{
    @Override
    public Map<String, Integer> countStatistic(List<LogRecord> logRecords) {
        Map<String, Integer> codeMap = new HashMap<>();
        for (var log : logRecords) {
            codeMap.merge(log.status(), 1, Integer::sum);
        }
        return codeMap;
    }
}
