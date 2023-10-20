package edu.hw2.task3.connection;

public class ConnectionException extends RuntimeException {

    public ConnectionException(Throwable cause) {
        super(cause);
    }

    public ConnectionException() { }

    public ConnectionException(String s) {
        super(s);
    }
}
