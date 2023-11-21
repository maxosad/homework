package edu.hw7.task4;

@SuppressWarnings("MagicNumber")
public class Util {

    private Util() { }

    private static double distanceBetweenDots(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static boolean inCircle(double x, double y) {
        double circleCenterX = 0.5;
        double circleCenterY = 0.5;
        double radius = 0.5;
        return distanceBetweenDots(circleCenterX, circleCenterY, x, y) <= radius;
    }
}
