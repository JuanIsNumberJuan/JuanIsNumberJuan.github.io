import javax.swing.JOptionPane;
import java.util.*;
public class SudokuGui {

   int size = 9;
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

   public SudokuGui(){
     String row;
 	   int num, y;

     int a = 0;
     for(int x = 1; x <= 9; x++){
       y=0;
       row = JOptionPane.showInputDialog("Enter numbers in row #" + x +" separated by a space if number not given enter 0");
       Scanner scan = new Scanner(row);
       while(scan.hasNext())
         if(scan.hasNextInt()){
           board[x-1][y] = scan.nextInt();
           y++;
         }
     }
     if(solve(board)){
       showOutput(printBoard(board));
     }
     else{
       showOutput(printBoard(board));
       //showOutput("Sudoku Board is unsolvable");
     }
     System.exit(0);
   }



   void showOutput(String a){
     //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
     JOptionPane.showMessageDialog(null, a , "Results", JOptionPane.PLAIN_MESSAGE );
   }

   boolean isNumbInRow(int[][] board , int number, int row){
     for(int x = 0; x < size; x++){
       if(board[row][x] == number){
         return true;
       }
     }
     return false;
   }

   public boolean isNumbInCol(int[][] board , int number, int col){
     for(int x = 0; x < size; x++){
       if(board[x][col] == number){
         return true;
       }
     }
     return false;
   }

   public boolean isNumbInBox(int[][] board , int number, int row, int col){
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

   public boolean Location(int[][] board , int number, int row, int col){
     return !isNumbInRow(board, number, row) &&
         !isNumbInCol(board,number, col) &&
         !isNumbInBox(board, number, row, col);
   }

   public boolean solve(int[][] board){
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

   public String printBoard(int[][] board){
     String a = "";
     for(int row = 0; row < size; row++){
       if(row%3==0 && row != 0){
         a += "\n-----------\n";
       }
       for(int col = 0;  col < size; col++){
         if(col%3==0 && col != 0){
           a+= "|";
         }
         a += board[row][col];
       }
       a += "\n";
     }
     return a;
   }

}
