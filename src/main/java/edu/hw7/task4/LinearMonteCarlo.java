package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import java.security.SecureRandom;
import java.time.LocalTime;

public class LinearMonteCarlo {
    private int totalCount;
    private int circleCount;
    private final long time;

    private final double pi; // pi = 4 * (circleCount / totalCount);

    public LinearMonteCarlo(int experiments) {
        long startTime = System.currentTimeMillis();
        SecureRandom rand = new SecureRandom();
        for (int experiment = 0; experiment < experiments; experiment++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            totalCount++;
            if (Util.inCircle(x, y)) {
                circleCount++;
            }
        }
        pi = 4 * (1.0 * circleCount / totalCount);
        time = System.currentTimeMillis() - startTime;
    }

    public double getPiError() {
        return Math.abs(pi - Math.PI);
    }

    public double getPi() {
        return pi;
    }

    public long getTime() {
        return time;
    }

    /**
     * 10_000, 100_000, 1_000_000, 10_000_000
     * @param args
     */
    public static void main(String[] args) {
        LinearMonteCarlo lm10000 = new LinearMonteCarlo(10000);
        LinearMonteCarlo lm100000 = new LinearMonteCarlo(100000);
        LinearMonteCarlo lm1000000 = new LinearMonteCarlo(1000000);
        LinearMonteCarlo lm10000000 = new LinearMonteCarlo(10000000);
//        142
//        395
//        2640
//        26251
        System.out.println(lm10000.getTime());
        System.out.println(lm100000.getTime());
        System.out.println(lm1000000.getTime());
        System.out.println(lm10000000.getTime());
    }





}
