package edu.hw3.Task7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task7Test {
    @Test
    public void test() {
        final String TEST_MESSAGE = "test";
        Task7.tree.put(null, TEST_MESSAGE);
        assertTrue(Task7.tree.containsKey(null));
        assertEquals(TEST_MESSAGE, Task7.tree.get(null));
    }
}
