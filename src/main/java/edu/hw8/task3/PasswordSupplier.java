package edu.hw8.task3;

import java.util.function.Supplier;

@SuppressWarnings({"UncommentedMain", "MagicNumber", "RegexpSinglelineJava"})
public class PasswordSupplier implements Supplier<String> {

    private final int positions;

    private int[] currentIndexesOfChar;
    private static final char[] ALLOWED_CHARS;

    static {
        ALLOWED_CHARS = new char[36];
        int i = 0;
        for (char c = '0'; c <= '9'; c++) {
            ALLOWED_CHARS[i++] = c;
        }

        for (char c = 'a'; c <= 'z'; c++) {
            ALLOWED_CHARS[i++] = c;
        }
    }

    public PasswordSupplier(int positions) {
        this.positions = positions;
        currentIndexesOfChar = new int[positions];
    }


    @Override
    public String get() {
        StringBuilder sb = new StringBuilder(positions);
        for (int positionIndex = 0; positionIndex < positions; positionIndex++) {
            sb.append(ALLOWED_CHARS[currentIndexesOfChar[positionIndex]]);
        }

        int incIndex = positions - 1;
        int allowedLetterIndex;
        while (incIndex >= 0) {
            allowedLetterIndex = currentIndexesOfChar[incIndex];
            if (ALLOWED_CHARS[allowedLetterIndex] == 'z') {
                currentIndexesOfChar[incIndex] = 0;
                incIndex--;
            } else {
                currentIndexesOfChar[incIndex] = allowedLetterIndex + 1;
                break;
            }
        }
        return sb.toString();



    }

    public static void main(String[] args) {
        PasswordSupplier supplier = new PasswordSupplier(2);
        int cnt = 0;
        while (cnt < 2) {
            var s = supplier.get();
            if (s.equals("00")) {
                cnt++;
            }
            System.out.println(s);
        }

    }
}
