package edu.hw9.task1;

import edu.hw9.task1.Metric;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private final ExecutorService executor;
    private final ConcurrentLinkedQueue<Metric> statQueue = new ConcurrentLinkedQueue<>();

    public StatsCollector(int nThreads) {
        executor = Executors.newFixedThreadPool(nThreads);
    }

    public void push(String name, double[] data) {
        Runnable countStatisticTask = () -> {
            DoubleSummaryStatistics stat = Arrays.stream(data).summaryStatistics();
            statQueue.add(new Metric(name, stat.getSum(), stat.getAverage(), stat.getMax(), stat.getMin()));
        };
        executor.execute(countStatisticTask);
    }

    public List<Metric> stats() {
        return new ArrayList<>(statQueue);
    }
}
