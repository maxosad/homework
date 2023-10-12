package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    void countDigitsZero() {
        assertThat(Task2.countDigits(0)).isOne();
    }


    @Test
    void countDigitsOneShouldReturnOne() {
        assertThat(Task2.countDigits(1)).isOne();
    }


    @Test
    void countDigitsTenShouldReturnTwo() {
        assertEquals(Task2.countDigits(10), 2);
    }

    @Test
    void countDigitsFiveFourFourShouldReturnThree() {
        assertEquals(Task2.countDigits(544), 3);
    }

    @Test
    void countDigitsFourSixSixSixShouldReturnFour() {
        assertEquals(Task2.countDigits(4666), 4);
    }


}