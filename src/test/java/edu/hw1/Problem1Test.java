package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {
    private Problem1 problem1;

    @BeforeEach
    void setUp() {
        problem1 = new Problem1();
    }

    @Test
    void sayHello() {
        //given
        String word = "hello";
        //when
        String response = problem1.say(word);
        //then
        Assertions.assertThat(response).isEqualToIgnoringCase("world");
    }

    @Test
    @DisplayName("Method should return pong on ping input")
    void sayPing() {
        //given
        String word = "ping";
        //when
        String response = problem1.say(word);
        //then
        Assertions.assertThat(response).isEqualToIgnoringCase("pong");
    }

    @Test
    void sayOtherShouldThrowException() {
        String word = "other";
        Assertions.assertThatThrownBy(() -> problem1.say(word)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void sayOtherShouldThrowException2() {
        String word = "other";
        assertThrows(IllegalArgumentException.class, () -> problem1.say(word)); 
    }
}