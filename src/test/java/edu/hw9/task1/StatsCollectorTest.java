package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class StatsCollectorTest {

    public static final double EPS = 1e-6;

    @Test
    void onePush() throws InterruptedException {
        StatsCollector collector = new StatsCollector(3);

        collector.push("metric_name", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        Thread.sleep(1000);
        for (var metric: collector.stats()) {
            assertEquals("metric_name", metric.getName());
            assertTrue(Math.abs(6.95 - metric.getSum()) < EPS);
            assertTrue(Math.abs(1.39 - metric.getAverage()) < EPS);
            assertTrue(Math.abs(5.1 - metric.getMax()) < EPS);
            assertTrue(Math.abs(0.05 - metric.getMin()) < EPS);
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
                "data1", new Metric("data1", 15.0, 5, 5.0, 1.0),
                "data2", new Metric("data2", 8.25, 5, 4.0, 0.05),
                "data3", new Metric("data3", 0.0, 1, 0.0, 0.0),
                "data4", new Metric("data4", 1.351, 3, 1.12, 0.101),
                "data5", new Metric("data5", 0.381, 3, 0.131, 0.12),
                "data6", new Metric("data6", 0.852, 3, 0.612, 0.11),
                "data7", new Metric("data7", 0.383, 3, 0.153, 0.11),
                "data8", new Metric("data8", 0.36, 3, 0.13, 0.11),
                "data9", new Metric("data9", 0.381, 3, 0.131, 0.12),
                "data10", new Metric("data10", 1.143, 3, 0.913, 0.11)
            );

            for (var metric : collector.stats()) {
                assertEquals(expected.get(metric.getName()).getName(), metric.getName());
                assertTrue(Math.abs(expected.get(metric.getName()).getSum() - metric.getSum()) < EPS);
                assertTrue(Math.abs(expected.get(metric.getName()).getAverage() - metric.getAverage()) < EPS);
                assertTrue(Math.abs(expected.get(metric.getName()).getMax() - metric.getMax()) < EPS);
                assertTrue(Math.abs(expected.get(metric.getName()).getMin() - metric.getMin()) < EPS);
            }
        } catch (InterruptedException e) {
            fail(e);
        }
    }


    @Test
    void multiplePushOfOneType() {
        try(ExecutorService executor = Executors.newFixedThreadPool(3)) {
            LinkedBlockingQueue<Data> dataQueue = new LinkedBlockingQueue<>();
            dataQueue.put(new Data("data1", new double[]{1,2,3,4,5}));
            dataQueue.put(new Data("data1", new double[]{1,2,3,4,5}));
            dataQueue.put(new Data("data1", new double[]{1,2,3,4,5}));
            dataQueue.put(new Data("data1", new double[]{1,2,3,4,5}));
            dataQueue.put(new Data("data1", new double[]{1,2,3,4,5}));
            dataQueue.put(new Data("data1", new double[]{1,2,3,4,5}));
            StatsCollector collector = new StatsCollector(3);

            Runnable takeFromQueueAndCountStat = () -> {
                try {
                    Data data = dataQueue.take();
                    collector.push(data.name(), data.data());
                } catch (InterruptedException e) {
                    fail(e);
                }
            };
            for (int i = 0; i < 6; i++) {
                executor.execute(takeFromQueueAndCountStat);
            }
            Thread.sleep(1000);


            Map<String, Metric> expected = Map.of(
                "data1", new Metric("data1", 90.0, 30, 5.0, 1.0)
            );

            for (var metric : collector.stats()) {
                assertEquals(expected.get(metric.getName()).getName(), metric.getName());
                assertTrue(Math.abs(expected.get(metric.getName()).getSum() - metric.getSum()) < EPS);
                assertTrue(Math.abs(expected.get(metric.getName()).getAverage() - metric.getAverage()) < EPS);
                assertTrue(Math.abs(expected.get(metric.getName()).getMax() - metric.getMax()) < EPS);
                assertTrue(Math.abs(expected.get(metric.getName()).getMin() - metric.getMin()) < EPS);
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
