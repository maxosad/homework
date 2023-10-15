package edu.hw2.task4;

public class CollingInfoFunction {

    private CollingInfoFunction() { }
    public static CallingInfo callingInfo() {
        Throwable thr = new Throwable();
        StackTraceElement[] stackTrace = thr.getStackTrace();
        int stackLength = stackTrace.length;

        return new CallingInfo(stackTrace[stackLength - 1].getClassName(), stackTrace[stackLength - 1].getMethodName());
    }


}
