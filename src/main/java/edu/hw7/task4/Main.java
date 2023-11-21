package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import java.util.List;

public class Main {
    private Main() { }
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        var logger = LogManager.getLogger();
        List<Integer> experimentsSize = List.of(10000, 100000, 1000000, 10000000);
        for (var expSize : experimentsSize) {
            LinearMonteCarlo lm = new LinearMonteCarlo(expSize);
            ParallelMonteCarlo pm = new ParallelMonteCarlo(2, expSize);
            logger.info("Linear Error for {} iterations: {}\n", expSize, lm.getPiError());
            logger.info("Parallel Error for {} iterations: {}\n", expSize, pm.getPiError());
        }

        int indexOfExperimentCount = 3;
        LinearMonteCarlo lm = new LinearMonteCarlo(experimentsSize.get(indexOfExperimentCount));
//        logger.info(lm.getTime());

        for (int threadsCount = 2; threadsCount < 6; threadsCount++) {
            ParallelMonteCarlo pm = new ParallelMonteCarlo(threadsCount, experimentsSize.get(indexOfExperimentCount));
            var mul = (1.0 * lm.getTime()) / (1.0 * pm.getTime());
            logger.info("Multiplier of parallelism with {} threads is {}",
                threadsCount,
                mul);
        }
    }
}
