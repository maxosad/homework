package edu.hw10.task1.generators;

public class StringGenerator extends AbstractGenerator<String>{

    public static final int TARGET_STRING_LENGTH = 10;

    @Override
    public String generate() {

        int leftLimit = 48; // цифра '0'
        int rightLimit = 122; // буква 'z'

        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(TARGET_STRING_LENGTH)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        return generatedString;
    }

}
