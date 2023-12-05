package edu.hw9;

import edu.hw9.task1.Metric;
import edu.hw9.task1.StatsCollector;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import static org.junit.jupiter.api.Assertions.*;

class StatsCollectorTest {

    public static final double EPS = 1e-6;

    @Test
    void onePush() throws InterruptedException {
        StatsCollector collector = new StatsCollector(3);

        collector.push("metric_name", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        Thread.sleep(1000);
        for (var metric: collector.stats()) {
            assertEquals("metric_name", metric.name());
            assertTrue(Math.abs(6.95 - metric.sum()) < EPS);
            assertTrue(Math.abs(1.39 - metric.average()) < EPS);
            assertTrue(Math.abs(5.1 - metric.max()) < EPS);
            assertTrue(Math.abs(0.05 - metric.min()) < EPS);
        }
    }

    @Test
    void multiplePush() {
        try(ExecutorService executor = Executors.newFixedThreadPool(3)) {
            LinkedBlockingQueue<Data> dataQueue = getData();
            StatsCollector collector = new StatsCollector(3);

            Runnable takeFromQueueAndCountStat = () -> {
                try {
                    Data data = dataQueue.take();
                    collector.push(data.name(), data.data());
                } catch (InterruptedException e) {
                    fail(e);
                }
            };
            for (int i = 0; i < 10; i++) {
                executor.execute(takeFromQueueAndCountStat);
            }
            Thread.sleep(1000);


            Map<String, Metric> expected = Map.of(
                "data1", new Metric("data1", 15.0, 3.0, 5.0, 1.0),
                "data2", new Metric("data2", 8.25, 1.65, 4.0, 0.05),
                "data3", new Metric("data3", 0.0, 0.0, 0.0, 0.0),
                "data4", new Metric("data4", 1.351, 0.4503333333333333, 1.12, 0.101),
                "data5", new Metric("data5", 0.381, 0.127, 0.131, 0.12),
                "data6", new Metric("data6", 0.852, 0.284, 0.612, 0.11),
                "data7", new Metric("data7", 0.383, 0.12766666666666668, 0.153, 0.11),
                "data8", new Metric("data8", 0.36, 0.12, 0.13, 0.11),
                "data9", new Metric("data9", 0.381, 0.127, 0.131, 0.12),
                "data10", new Metric("data10", 1.143, 0.381, 0.913, 0.11)
            );

            for (var metric : collector.stats()) {
                assertEquals(expected.get(metric.name()).name(), metric.name());
                assertTrue(Math.abs(expected.get(metric.name()).sum() - metric.sum()) < EPS);
                assertTrue(Math.abs(expected.get(metric.name()).average() - metric.average()) < EPS);
                assertTrue(Math.abs(expected.get(metric.name()).max() - metric.max()) < EPS);
                assertTrue(Math.abs(expected.get(metric.name()).min() - metric.min()) < EPS);
            }
        } catch (InterruptedException e) {
            fail(e);
        }
    }

    @NotNull private static LinkedBlockingQueue<Data> getData() throws InterruptedException {
        LinkedBlockingQueue<Data> dataQueue = new LinkedBlockingQueue<>();
        dataQueue.put(new Data("data1", new double[]{1,2,3,4,5}));
        dataQueue.put(new Data("data2", new double[]{1,0.2,3,4,0.05}));
        dataQueue.put(new Data("data3", new double[]{0.0}));
        dataQueue.put(new Data("data4", new double[]{0.101, 1.12, 0.13}));
        dataQueue.put(new Data("data5", new double[]{0.131, 0.12, 0.13}));
        dataQueue.put(new Data("data6", new double[]{0.11, 0.612, 0.13}));
        dataQueue.put(new Data("data7", new double[]{0.11, 0.12, 0.153}));
        dataQueue.put(new Data("data8", new double[]{0.11, 0.12, 0.13}));
        dataQueue.put(new Data("data9", new double[]{0.131, 0.12, 0.13}));
        dataQueue.put(new Data("data10", new double[]{0.11, 0.12, 0.913}));
        return dataQueue;
    }
}
