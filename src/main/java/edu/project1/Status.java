package edu.project1;

public enum Status {
    IN_PROGRESS,
    WON("You win"),
    LOST("You lost");

    private String message;

    Status(String message) {
        this.message = message;
    }

    Status() {}
}
