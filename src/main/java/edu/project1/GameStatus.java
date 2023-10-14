package edu.project1;

public enum GameStatus {
    IN_PROGRESS,
    LOST("You lost"),
    WIN("You win");

    private String message;

    public String getMessage() {
        return this.message;
    }

    GameStatus(String message) {
        this.message = message;
    }

    GameStatus() {}

}
