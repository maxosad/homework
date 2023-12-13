package edu.hw10.task1.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class MyClassWithAnnotations {
    private int minNumber;
    private int maxNumber;
    ;

    public MyClassWithAnnotations(@Max(11) @Min(10) int minNumber,  @Max(20) @Min(20) int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public static MyClassWithAnnotations getInstance(@Max(11) @Min(10) int minNumber,@Max(20) @Min(20) int maxNumber) {
        return new MyClassWithAnnotations(minNumber, maxNumber);
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }
}
