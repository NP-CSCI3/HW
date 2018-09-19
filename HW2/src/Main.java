/**
 * <h1>HW2 - N-Queens</h1>
 * This program attempts to place n queens on an n x n board
 *
 * @author  Jim Kravchenko
 * @version 1.0
 * @created 2018-09-12
 * @lastedit 2018-09-19 4:37 PM
 *
 * WORKING COPY - ASSIGNMENT COMPLETE
 */
import java.util.Stack;
import java.util.Scanner;



public class Main {
    public Main() {
    }

      static boolean checkValid(int board[], int row) {
        for (int i = row - 1; i >= 0; i--) { // check all before current row
            if ((board[i] == board[row])
                    || (Math.abs(board[row] - board[i]) == Math.abs(row - i))) {
                return false;
            }
        }
        return true;
    }

    static void printBoard(int board[]) {
        String space = "|_|";
        String queen = "|Q|";
//
        for (int i = 0; i < board.length; i++) {
            String preQueen = "";
            String postQueen = "";
            System.out.print(i+": ");
            for (int j = 0; j < board[i]; j++) {
                preQueen += space;
            }
            for (int j = 0; j < (board.length - board[i])-1; j++) {
                postQueen += space;
            }
            System.out.println(preQueen + queen + postQueen);
        }

        System.out.println();
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


    public static void main(String[] args) {
        //  vars
        //
        int row = 0;     // used to iterate through indicies of array
        int col = 0;     // keeps track of active column position; copied to array board[row]
        int filled = 0;   // keeps track of stack height for successfully filled rows.
        int n=0;       // board size and number of queens

        int count = 0;  //  board combination count
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Desired Board Size: ");
        n = scanner.nextInt();
        int lastIndex = n - 1;

        // declare an array board[] for keeping track of the state of the current board
        int board[] = new int[n];

        // declare a stack boardStack for backtracking through the rows
        Stack boardStack = new Stack();
        for (row = 0; row <= lastIndex; row++) {         //  iterate through the rows

            for (col = board[row]; col <= lastIndex; col++) {//  iterate through the cols in row

                board[row] = col;   // add column to array

                if (!checkValid(board, row)) {  //  test if placement is invalid
                    if (col == lastIndex) {     //  if invalid and col is lastIndex, backtrack
                        //backtrack

                        while (board[row] == lastIndex) {
                            if (row == 0) {
                                System.out.println("No more valid configs");
                                System.out.println("Total Valid Combinations: " + count);
                                count=0;
                                return;
                            }
                            board[row] = 0;
                            boardStack.pop(); // pop stack
                            filled--;        // decrease filled
                            row--;           //  go back to previous row in current for loop
                        }

                        if (board[row] < lastIndex) {
                            board[row]++;   // update previous col
                        }

                        if (board[row] != 0) {
                            row--;
                        }
                        break;          // break out and re-enter inner for loop, updating col to prevcol+1
                        //  redo last row and start at its col value+1
                    }
                    continue;           //  if placement invalid and col not lastIndex, move on to next col

                } else if (checkValid(board, row)) {  // test if placement is valid.

                    boardStack.push(board[row]);
                    filled++;
                    break;
                }
            }
                if (boardStack.size() == n) {            //    break if the size of the stack is the size of the board (no pop)
                    // break;
                    count++;
                    printBoard(board);
                    boardStack.pop();
                    filled--;
                    if (col < lastIndex) {
                        col++;
                        board[row]=col;

                    }
                    else {
                        board[row] = 0;
                        row--;
                        //col = board[row]++;
                        board[row]++;


                        boardStack.pop();
                        filled--;
                    }
                    row--;
                    continue;


            }
        }
    }
}

