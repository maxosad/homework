package edu.hw8.task1;

public class C2 {
    public static void main(String[] args) {
        int port = 3345;
        Client client = new Client(port);
        String ans = client.send("интеллект");
        System.out.println(ans);
    }
}
