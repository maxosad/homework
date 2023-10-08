package edu.hw1;

public class Task1 {
    public static Integer minutesToSeconds(String minutesSeconds) throws IllegalArgumentException{
        String[] minSec =  minutesSeconds.split(":");
        Integer minutes = Integer.parseInt(minSec[0]);
        Integer seconds = Integer.parseInt(minSec[1]);
        if (seconds >= 60) {
           return -1;
        }
        return minutes * 60 + seconds;
    }
}
