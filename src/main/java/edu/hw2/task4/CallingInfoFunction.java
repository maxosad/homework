package edu.hw2.task4;

public class CallingInfoFunction {

    private CallingInfoFunction() { }

    public static CallingInfo callingInfo() {
        Throwable thr = new Throwable();
        StackTraceElement[] stackTrace = thr.getStackTrace();
//        int stackLength = stackTrace.length;
//        System.out.println("stackLength" +  " " + stackLength);
//        for (var el : stackTrace) {
//            System.out.println(el.getClassName() + " " + el.getMethodName());
//        }

        return new CallingInfo(stackTrace[1].getClassName(), stackTrace[1].getMethodName());
    }


}
