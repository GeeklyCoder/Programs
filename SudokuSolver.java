package com.dsapractice;
import java.util.*;

public class SudokuSolver {
    public static boolean isSafe(char [][]board, int row, int col, int number) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char)(number + '0')) {
                return false;
            }

            if (board[row][i] == (char)(number + '0')) {
                return false;
            }
        }

        int gridRow = (row / 3) * 3;
        int gridCol = (col / 3) * 3;

        for (int i = gridRow; i < gridRow + 3; i++) {
            for (int j = gridCol; j < gridCol + 3; j++) {
                if (board[i][j] == (char)(number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean helper(char [][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }

        int newRow = 0;
        int newCol = 0;

        if (col != board.length - 1) {
            newRow = row;
            newCol = col + 1;
        } else {
            newRow = row + 1;
            newCol = 0;
        }

        if (board[row][col] != '.') {
            if (helper(board, newRow, newCol)) {
                return true;
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char)(i + '0');
                    if (helper(board, newRow, newCol)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the rows of the board : ");
        int rows = sc.nextInt();

        System.out.println("Enter the columns of the board : ");
        int cols = sc.nextInt();

        char [][]board = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("Enter the " + i + ", " + j + " number in the board");
                char c = sc.next().charAt(0);
                board[i][j] = c;
            }
        }

        helper(board, 0, 0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
