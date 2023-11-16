package edu.project3.StatisticCounter.AdditionalStat;

import edu.project3.StatisticCounter.StatisticCounter;
import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUserAgentStatisticCounter implements StatisticCounter<Map<String, Integer>> {
    public static final String title = "HttpUserAgentStatisticCounter";
    public static final String keyName = "HttpUserAgent";
    public static final String valueName = "Quantity";
    @Override
    public Statistic<Map<String, Integer>> countStatistic(List<LogRecord> logRecords) {
        Map<String, Integer> ans = new HashMap<>();
        for (var log : logRecords) {
            ans.merge(log.httpUserAgent(), 1, Integer::sum);
        }
        return new Statistic<>(title, keyName, valueName, ans);
    }
}
