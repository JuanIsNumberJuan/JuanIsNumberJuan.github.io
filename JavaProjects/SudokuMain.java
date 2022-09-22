import java.util.Scanner;
import javax.swing.*;
public class SudokuMain{
  private static int size = 9;
  private static Scanner scan = new Scanner(System.in);

  public static void main(String[] args){
    SudokuGui test = new SudokuGui();
  }
}

/*`
    int [][] board = {
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };


    askUser(board);

    printBoard(board);
    if(solve(board)){
      System.out.println("\n\n");
      printBoard(board);
    }
    else{
      System.out.println("\nSudoku Board is unsolvable");
      printBoard(board);
    }
  }

  private static void askUser(int[][] board){
    int row, col,  num;

    System.out.println("How many numbers are in the Sudoku?");
    int amount = scan.nextInt();

    for(int x = 0; x < amount; x++){
      System.out.println("Enter box number (1-9): ");
      row = scan.nextInt();
      System.out.println("Enter small box number (1-9): ");
      col = scan.nextInt();
      System.out.println("Enter number (1-9): ");
      num = scan.nextInt();
      if((0<row) && (row<10) && (0<col) && (col<10) && (0<num) &&(num<10)){
          board[row-1][col-1] = num;
      }
      else{
        System.out.println("Row, Column, and number need to be between 1 - 9");
        x--;
      }
    }
  }

  private static boolean isNumbInRow(int[][] board , int number, int row){
    for(int x = 0; x < size; x++){
      if(board[row][x] == number){
        return true;
      }
    }
    return false;
  }


  private static boolean isNumbInCol(int[][] board , int number, int col){
    for(int x = 0; x < size; x++){
      if(board[x][col] == number){
        return true;
      }
    }
    return false;
  }

  private static boolean isNumbInBox(int[][] board , int number, int row, int col){
    int row2 = row - (row%3);
    int col2 = col - (col%3);
    //System.out.println(row2 + "  rows " + row3 + "\n" + col2 + " cols " + col3);
    for(int x = 0; x < 3; x++){
      for(int y = 0; y < 3; y++){
        if(board[row2+x][col2+y] == number){
          return true;
        }
      }
    }
    return false;
  }

  private static boolean Location(int[][] board , int number, int row, int col){
    return !isNumbInRow(board, number, row) &&
        !isNumbInCol(board,number, col) &&
        !isNumbInBox(board, number, row, col);
  }

  private static boolean solve(int[][] board){
    for(int row = 0; row < size; row++){
      for(int col = 0; col < size; col++){
        if(board[row][col] == 0){
          for(int num = 1; num <= size; num++){
            if(Location(board, num, row, col)){
              board[row][col] = num;
              if(solve(board)){
                return true;
              }
              else{
                board[row][col] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  private static void printBoard(int[][] board){
    for(int row = 0; row < size; row++){
      if(row%3==0 && row != 0){
        System.out.println("-----------");
      }
      for(int col = 0;  col < size; col++){
        if(col%3==0 && col != 0){
          System.out.print("|");
        }
        System.out.print(board[row][col]);
      }
      System.out.println();
    }
  }
}
*/
