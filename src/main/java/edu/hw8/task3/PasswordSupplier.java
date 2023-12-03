package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings({"UncommentedMain", "MagicNumber", "RegexpSinglelineJava"})
public class PasswordSupplier implements Supplier<String> {

    private final int positions;

    private List<Integer> currentIndexesOfChar;
    private static final List<Character> ALLOWED_CHARS;

    static {
        ALLOWED_CHARS = new ArrayList<>();

        for (Character c = '0'; c <= '9'; c++) {
            ALLOWED_CHARS.add(c);
        }

        for (Character c = 'a'; c <= 'z'; c++) {
            ALLOWED_CHARS.add(c);
        }
    }

    public PasswordSupplier(int positions) {
        this.positions = positions;
        currentIndexesOfChar = new ArrayList<>(positions);
        for (int position = 0; position < positions; position++) {
            currentIndexesOfChar.add(0);
        }
    }


    @Override
    public String get() {
        StringBuilder sb = new StringBuilder(positions);
        for (int positionIndex = 0; positionIndex < positions; positionIndex++) {
           sb.append(ALLOWED_CHARS.get(currentIndexesOfChar.get(positionIndex)));
        }
        int currentIndexToIncrement = positions - 1;
        int allowedCharacterIndex;

        while (currentIndexToIncrement >= 0) {
            allowedCharacterIndex = currentIndexesOfChar.get(currentIndexToIncrement);
            if (ALLOWED_CHARS.get(allowedCharacterIndex).equals('z')) {
                currentIndexesOfChar.add(currentIndexToIncrement, 0);
                currentIndexToIncrement--;
            } else {
                currentIndexesOfChar.add(currentIndexToIncrement, allowedCharacterIndex + 1);
                break;
            }
        }

       return sb.toString();
    }

    public static void main(String[] args) {
        PasswordSupplier supplier = new PasswordSupplier(4);
        while (true) {
            System.out.println(supplier.get());
        }
    }
}
