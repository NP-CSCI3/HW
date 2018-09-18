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

    // Steps
    //  1. Main
    //  2. checkValid
    //  3. displayBoard

    //  Functions
    //  checkValid  -   checks if board position is valid and returns boolean
    //  printBoard  -   outputs a valid board state
    //  main        -   runs program

    // static - When a member is declared static, it can be accessed before any objects of its class are created, and without reference to any object.


//      checkValid  -   checks if board position is valid and returns boolean
//          //  when a queen is placed, it must be checked against all previous positions
//          //  there are two cases that result in a conflict and a return false
//          //  1. a previous row has the same col value ( col = prevcol )
//          //  2. a previous row is on a diagonal
//          //      a diagonal exists when the absolute distance between cols = to the distance of rows
//          //      (col - prevcol) = (row - prevrow)
//          //
//          //  a for loop checks the array at the current row to get its current col
//          //  the array decrements a row and checks for equality or diagonal and returns boolean
//          //  this repeats until row = 0, then returns true

    static boolean checkValid(int board[], int row) {
       // System.out.println("CheckValid()");
        //   i <- prevrow,  board[i] <- prevcol | row <- currentrow board[row] <- currentcol,
        for (int i = row - 1; i >= 0; i--) { // check all before current row
            if ((board[i] == board[row])
                    || (Math.abs(board[row] - board[i]) == Math.abs(row - i))) {
                return false;
            }
        }
        return true;
    }


    static void printBoard(int board[]) {
        for (int i = 0; i < board.length; i++) {
            System.out.print(" " + board[i] + ", ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        //  vars
        //
        int row = 0;     // used to iterate through indicies of array
        int col = 0;     // keeps track of active column position; copied to array board[row]
        int filled = 0;   // keeps track of stack height for successfully filled rows.
        int n = 5;       // board size and number of queens
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

     //
        //   printBoard(board);
    }
}

