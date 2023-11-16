package edu.project3.util;

public class Util {
    private Util() { }

    public static final String dataPath = "src/main/java/edu/project3/data";

    public static String middleAlignmentFixSize(String str, String additionalSymbol, int expectedLen) {
        int len = str.length();
        if (len > expectedLen) {
            throw new IllegalArgumentException("str length must be less then expected length");
        }
        StringBuilder sb = new StringBuilder(expectedLen);
        int diff = expectedLen - len;
        int addLeft = (diff + 1) / 2;
        int addRight = diff - addLeft;

        for (int i = 0; i < addLeft; i++) {
            sb.append(additionalSymbol);
        }
        sb.append(str);
        for (int i = 0; i < addRight; i++) {
            sb.append(additionalSymbol);
        }
        return sb.toString();
    }

    public static String rightAlignmentFixSize(String str, String additionalSymbol, int expectedLen) {
        int len = str.length();
        if (len > expectedLen) {
            throw new IllegalArgumentException("str length must be less then expected length");
        }
        StringBuilder sb = new StringBuilder(expectedLen);
        int diff = expectedLen - len;

        for (int i = 0; i < diff; i++) {
            sb.append(additionalSymbol);
        }
        sb.append(str);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(rightAlignmentFixSize("qwer","*", 4));
        System.out.println(middleAlignmentFixSize("qwer","*", 5));
    }
}
