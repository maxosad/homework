package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentResource implements StatisticCounter<Map<String, Integer>>{
    public static final String title = "MostFrequentResource";
    public static final String keyName = "Resource";
    public static final String valueName = "Quantity";

    @Override
    public Statistic<Map<String, Integer>> countStatistic(List<LogRecord> logRecords) {
        Map<String, Integer> codeMap = new HashMap<>();
        for (var log : logRecords) {
            codeMap.merge(log.resource(), 1, Integer::sum);
        }
        return new Statistic<>(title, keyName, valueName, codeMap);
    }
}
