package edu.hw8.task2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FixedThreadPoolTest {
    private static Stream<Arguments> provideNumberOfIncrements() {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(10),
            Arguments.of(100)
        );
    }

    @ParameterizedTest
    @DisplayName("execute increment test")
    @MethodSource("provideNumberOfIncrements")
    void execute(int expected) {
        AtomicInteger counter = new AtomicInteger(0);

        try (FixedThreadPool fixedThreadPool = FixedThreadPool.create(3)) {
            fixedThreadPool.start();
            for (int times = 0; times < expected; times++) {
                fixedThreadPool.execute(counter::incrementAndGet);
            }
            Thread.sleep(1000);
        } catch (Exception e ) {
            fail(e);
        }

        assertEquals(expected, counter.get());

    }
}
