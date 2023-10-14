package edu.hw1;

public class Task8 {
    private static int[][] theBoard;
    public static final int EIGHT = 8;

    public static Boolean knightBoardCapture(int[][] board) {
        theBoard = board;
        for (int i = 0; i < EIGHT; i++) {
            for (int j = 0; j < EIGHT; j++) {
                if (theBoard[i][j] == 1 && !checkKnight(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void setTheBoard(int[][] theBoard) {
        Task8.theBoard = theBoard;
    }

    public static boolean checkRange(int x, int y) {
        return (x >= 0 && x < EIGHT) && (y >= 0 && y < EIGHT);
    }

    public static int[][] sdvig = new int[][]{
        {Util.ONE, Util.TWO},
        {Util.ONE, Util.MINUS_TWO},
        {Util.TWO, Util.ONE},
        {Util.TWO, Util.MINUS_ONE},
        {Util.MINUS_ONE, Util.TWO},
        {Util.MINUS_ONE, Util.MINUS_TWO},
        {Util.MINUS_TWO, Util.ONE},
        {Util.MINUS_TWO, Util.MINUS_ONE}
    };
    public static boolean checkKnight(int x, int y) {
        int newX;
        int newY;
        for (int i = 0; i < 8; i++) {
            newX = x+sdvig[i][0];
            newY = y+sdvig[i][1];
            if (checkRange(newX, newY) && theBoard[newX][newY] == 1) {
                return false;
            }
        }
        return true;
    }

}
