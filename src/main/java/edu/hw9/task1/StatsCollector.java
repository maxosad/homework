package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private final ExecutorService executor;

    private final Map<String, Metric> metricMap = new ConcurrentHashMap<>();

    public StatsCollector(int nThreads) {
        executor = Executors.newFixedThreadPool(nThreads);
    }

    public void push(String name, double[] data) {
        Runnable countStatisticTask = () -> {
            if (data.length == 0) {
                return;
            }
            metricMap.putIfAbsent(name, new Metric(name, 0.0, 0, Double.MIN_VALUE, Double.MAX_VALUE));
            Metric currentMetric = metricMap.get(name);
            DoubleSummaryStatistics stat = Arrays.stream(data).summaryStatistics();
            currentMetric.addSum(stat.getSum());
            currentMetric.addMin(stat.getMin());
            currentMetric.addMax(stat.getMax());
            currentMetric.addCount(stat.getCount());
        };
        executor.execute(countStatisticTask);
    }

    public List<Metric> stats() {
        return new ArrayList<>(metricMap.values());
    }
}
