package edu.hw2.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallingInfoFunctionTest {

    @Test
    void callingInfoTest() {
        CallingInfo callingInfo = CallingInfoFunction.callingInfo();

        assertTrue(CallingInfoFunction.callingInfo() instanceof CallingInfo);
        assertEquals("callingInfoTest", callingInfo.methodName());
        assertEquals("edu.hw2.task4.CallingInfoFunctionTest", callingInfo.className());
    }
}
