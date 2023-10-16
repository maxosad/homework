package edu.hw1;

public class Task1 {

    private Task1() {}

    public static Integer minutesToSeconds(String minutesSeconds) throws IllegalArgumentException {
        final int SECONDS_IN_MINUTE = 60;
        String[] minSec =  minutesSeconds.split(":");
        int minutes = Integer.parseInt(minSec[0]);
        int seconds = Integer.parseInt(minSec[1]);
        if (seconds >= SECONDS_IN_MINUTE) {
           return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
