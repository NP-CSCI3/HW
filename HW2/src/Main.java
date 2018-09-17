/**
 * <h1>HW2 - N-Queens</h1>
 * This program attempts to place n queens on an n x n board
 *  * <p>
 *
 * @author  Jim Kravchenko
 * @version 1.0
 * @created 2018-09-12
 *
 * NOT WORKING COPY - ASSIGNMENT IN PROGRESS
 */

import java.util.Stack;



public class Main {
    public Main() {
    }

    //
    public static boolean checkValid(int board[], int row) {
        System.out.println("CheckValid()");
        for (int i = 0; i < row; i++) {
            if (row  == 0) break;
            System.out.println("Diff: " + Math.abs(board[row] - board[i]) + " = " + (row - i));
            if (Math.abs(board[row] - board[i]) == row-i) {

                System.out.println("conflict found at row: " + i + "col: " + board[i]);
                return false;
            } else if (board[row] == board[i]) {
                System.out.println("EQUAL conflict found at row: " + i + "col: " + board[i]);
                return false;
            }
            System.out.println("GOOD at row: " + i + "col: " + board[i]);
        }

        return true;

    }


    public static void main(String[] args) {
        int filled = 0; // filled positions on stack
        int n = 4;  // board size
        int board[] = new int[n];
        Stack boardStack = new Stack();
        System.out.println("Starting");
        for (int row = 0; row < n; row++) {
            System.out.println("Finding a place for row : " + row + "\n");
            for (int col = 0; col < n; col++) {
                board[row] = col; // copy this col to the array at index of row
                System.out.println("Trying col : " + col + " in row: " + row);
                System.out.println("Checking validity of row : " + row + " col: " + col);
                if (checkValid(board, row) == false) {
                    if ((col == (n - 1))) {
                        // no position found - backtrack

                        col = (int) boardStack.peek();
                        row-=1;
                        filled--;
                        System.out.println("Backtracking to col: " + col + " and row: " + row);
                        System.out.println("Fill Count: " + filled);
                        boardStack.pop();
                        continue;    // backtrack to previous row starting from it's col+1
                    }
                    continue;   // check next col
                } else {  // valid Position found
                    board[row] = col;
                    System.out.println("valid at row: " + row + " col: " + col);
                    boardStack.push(col); // row/col position found, push to stack
                    filled++;
                    System.out.print("row: " + row + " | ");
                    for (int qspace = 0; qspace < col ; qspace++) {
                        System.out.print(" * ");
                    }
                    System.out.println(" Q ");


                    }
                    break;
                }
            }



        for (int i = 0; i < board.length; i++) {
            System.out.println("row= " + i + " col= " + board[i]);
        }
    }
}
