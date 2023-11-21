package edu.hw7.task4;

import java.security.SecureRandom;

@SuppressWarnings({"MagicNumber"})
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






}
