package edu.hw1;

public class Task1 {
    public static Integer minutesToSeconds(String minutesSeconds) throws IllegalArgumentException{
        final int SIXTY = 60;
        String[] minSec =  minutesSeconds.split(":");
        int minutes = Integer.parseInt(minSec[0]);
        int seconds = Integer.parseInt(minSec[1]);
        if (seconds >= SIXTY) {
           return -1;
        }
        return minutes * SIXTY + seconds;
    }
}
