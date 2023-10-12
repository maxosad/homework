package edu.hw1;

public class Task8 {
    private static int[][] theBoard;

    public static Boolean knightBoardCapture(int[][] board) {
        theBoard = board;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (theBoard[i][j] == 1 && !checkKnight(i, j))
                    return false;
            }
        }
        return true;
    }

    public static void setTheBoard(int[][] theBoard) {
        Task8.theBoard = theBoard;
    }

    public static boolean checkRange(int x, int y) {
        return (x>=0 && x<8) && (y>=0 && y<8);
    }

    public static int[][] sdvig = new int[][]{
        {1,2},
        {1,-2},
        {2,1},
        {2,-1},
        {-1,2},
        {-1,-2},
        {-2,1},
        {-2,-1}
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
