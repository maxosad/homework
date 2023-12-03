package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import javax.xml.bind.DatatypeConverter;

@SuppressWarnings({"UncommentedMain", "MagicNumber", "RegexpSinglelineJava"})
public class LinearPasswordChecker extends AbstractChecker {

    private int countOfPassToGuess;

    public LinearPasswordChecker(int countOfPassToGuess) {
        this.countOfPassToGuess = countOfPassToGuess;
    }

    public void start() {
        while (countOfPassToGuess > 0) {
            String passString = nextPassword();
//            System.out.println(passString);
            MD.update(passString.getBytes(StandardCharsets.UTF_8));
            byte[] digest = MD.digest();
            String hashPassword = DatatypeConverter.printHexBinary(digest).toLowerCase();
            String name = hashName.get(hashPassword);
            if (name != null) {
                namePassword.put(name, passString);
                countOfPassToGuess--;
            }
        }
    }

    public static void main(String[] args) {
        LinearPasswordChecker checker = new LinearPasswordChecker(3);
        checker.readHashNameFile("src/main/java/edu/hw8/task3/files/loginAndHashedPasswords.txt");
        checker.start();
        System.out.println(checker.getNamePassword());

    }


}
