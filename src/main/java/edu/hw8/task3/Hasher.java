package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("MagicNumber")
public class Hasher {

    private Hasher() { }

    public static final MessageDigest MD;


    static {
        try {
            MD = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        String path = "src/main/java/edu/hw8/task3/files/loginAndHashedPasswords.txt";
////        md.update(pass.getBytes(StandardCharsets.UTF_8));
////        byte[] digest = md.digest();
////        String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
////        System.out.println(myHash);
//
//        String[] names = new String[]{"gaga", "you", "we"};
//        String[] pas = new String[]{"1234", "qwer", "pop"};
//        ArrayList<String> hashedPass = new ArrayList<>(3);
//        byte[] digest;
//
//        for (int i = 0; i < 3; i++) {
//            MD.update(pas[i].getBytes(StandardCharsets.UTF_8));
//            digest = MD.digest();
//            hashedPass.add(DatatypeConverter.printHexBinary(digest).toLowerCase());
//        }
//        try (FileWriter fw = new FileWriter(path)) {
//            for (int i = 0; i < 3; i++) {
//                fw.write(names[i] + " " + hashedPass.get(i) + "\n");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
}
