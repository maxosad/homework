package edu.hw8.task3;

@SuppressWarnings("MagicNumber")
public class LinearPasswordChecker extends AbstractChecker {

    private int countOfPassToGuess;

    public LinearPasswordChecker(int countOfPassToGuess) {
        this.countOfPassToGuess = countOfPassToGuess;
    }

    public void start() {
        while (countOfPassToGuess > 0) {
            String passString = nextPassword();
            String hashPassword = countMD5Hash(passString);
            String name = hashName.get(hashPassword);
            if (name != null && !namePassword.containsKey(name)) {
                namePassword.put(name, passString);
                countOfPassToGuess--;
            }
        }
    }
}
