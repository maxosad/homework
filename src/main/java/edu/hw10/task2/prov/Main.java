package edu.hw10.task2.prov;

public class Main {
    @LogExecutionTime
    public void serve() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) {
        Main m = new Main();
        try {
            m.serve();
        } catch (Exception ignore) { }
    }
}
