package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @Test
    void sayHello() {
        Problem1 problem1 = new Problem1();
        //given
        String word = "hello";
        //when
        String response = problem1.say(word);
        //then
        Assertions.assertThat(response).isEqualToIgnoringCase("world");
    }
}