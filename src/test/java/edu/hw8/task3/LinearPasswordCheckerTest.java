package edu.hw8.task3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class LinearPasswordCheckerTest {

    @Test
    void start() {
        Map<String, String> expected = new HashMap<>();
        expected.put("gaga", "1234");
        expected.put("you", "qwer");
        expected.put("we", "popo");
        LinearPasswordChecker checker = new LinearPasswordChecker(3);

        checker.readHashNameFile("src/main/java/edu/hw8/task3/files/loginAndHashedPasswords.txt");
        checker.start();

        assertEquals(expected, checker.getNamePassword());
    }
}
