import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TicTacToe{

  JFrame frame;
  JPanel panel;
  //Will use buttons to display the board
  JButton newGame = new JButton("New Game");
  JButton button0 = new JButton(" ");
  JButton button1 = new JButton(" ");
  JButton button2 = new JButton(" ");
  JButton button3 = new JButton(" ");
  JButton button4 = new JButton(" ");
  JButton button5 = new JButton(" ");
  JButton button6 = new JButton(" ");
  JButton button7 = new JButton(" ");
  JButton button8 = new JButton(" ");
  JLabel label = new JLabel(" ");

  //this will keep track of any change done to the board
  private String[][] board;
  private int turns;
  //Start with Player X this will change to Player O and back until game it over
  private String player = "X";
  // enable button will pretty much enable the buttons. if u have a winner the remaining buttons will do nothing
  boolean enableButton;


  public TicTacToe(){
    board = new String[3][3];
    turns = 0;  //games ends when 9 turns have passed
    enableButton = true;
    for ( int r=0; r<3; r++ )	{		//rows
      for ( int c=0; c<3; c++ ){	//columns
	       board[r][c] = " ";
      }
    }

    panel = new JPanel();
    panel.add(button0);
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);
    panel.add(button5);
    panel.add(button6);
    panel.add(button7);
    panel.add(button8);
    panel.add(newGame);

    //All Buttons will do the same thing
    button0.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //Checks if location is valid and if button are enabled
        if(isValid(0,0, player) && enableButton){
          button0.setText(player);        //sets button to player ("X or O")
          //Checks if there is a winner
          if(isWinner(player)){
            label.setText(wPlayer(player));   //sets label declaring winner
            enableButton = false; //disables all other buttons
          }
          //will change the players
          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });


    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(isValid(0,1, player)){
          button1.setText(player);
          if(isWinner(player) && enableButton){
            label.setText(wPlayer(player));
            enableButton = false;
          }

          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });

    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(isValid(0,2, player)){
          button2.setText(player);
          if(isWinner(player)){
            label.setText(wPlayer(player));
            enableButton = false;
          }

          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });


    button3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(isValid(1,0, player)){
          button3.setText(player);
          if(isWinner(player) && enableButton){
            label.setText(wPlayer(player));
            enableButton = false;
          }

          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });


    button4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(isValid(1,1, player) && enableButton){
          button4.setText(player);
          if(isWinner(player)){
            label.setText(wPlayer(player));
            enableButton = false;
          }

          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });

    button5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(isValid(1,2, player)){
          button5.setText(player);
          if(isWinner(player) && enableButton){
            label.setText(wPlayer(player));
            enableButton = false;
          }

          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });

    button6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(isValid(2,0, player) && enableButton){
          button6.setText(player);
          if(isWinner(player)){
            label.setText(wPlayer(player));
            enableButton = false;
          }

          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });

    button7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(isValid(2,1, player) && enableButton){
          button7.setText(player);
          if(isWinner(player)){
            label.setText(wPlayer(player));
            enableButton = false;
          }

          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });

    button8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(isValid(2,2, player) && enableButton){
          button8.setText(player);
          if(isWinner(player)){
            label.setText(wPlayer(player));
            enableButton = false;
          }

          if(player.equals("X")){
            player = "O";
          }
          else if(player.equals("O")){
            player = "X";
          }
        }
      }
    });

    //resets game
    newGame.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        enableButton = true;
        turns = 0;  //games ends when 9 turns have passed
        for ( int r=0; r<3; r++ ){		//rows
          for ( int c=0; c<3; c++ ){	//columns
  	         board[r][c] = " ";
          }
        }

        button0.setText(" ");
        button1.setText(" ");
        button2.setText(" ");
        button3.setText(" ");
        button4.setText(" ");
        button5.setText(" ");
        button6.setText(" ");
        button7.setText(" ");
        button8.setText(" ");
        label.setText(" ");
      }
    });

    //add everything to frame so its visible.
    panel.add(label);
    frame = new JFrame("TicTacToe");
    frame.add(panel);
    frame.setLocation(700,700);
    frame.setSize(300,300);
    frame.setResizable(false);
    frame.setVisible(true);


  }

  //checks if location is valid (if its empty)
  public boolean isValid( int r, int c, String a) {
    if (board[r][c] == " "){
      board[r][c] = a;
      return true;
    }
    return false;
  }


  //checks for a winner
  private boolean isWinner( String a ) {
    //checks rows
    for(int x = 0; x<3; x++){
      if(board[x][0] == a){
        if(board[x][1] == a){
          if(board[x][2] == a){
            return true;
          }
        }
      }
    }
    //checks column
    for(int x = 0; x<3; x++){
      if(board[0][x] == a){
        if(board[1][x] == a){
          if(board[2][x] == a){
            return true;
          }
        }
      }
    }
    //checks diagonal left to right
    for(int x = 0; x<3; x++){
      if(board[0][0] == a){
        if(board[1][1] == a){
          if(board[2][2] == a){
            return true;
          }
        }
      }
    }
    //checks diagonal right to left
    for(int x = 0; x<3; x++){
      if(board[0][2] == a){
        if(board[1][1] == a){
          if(board[2][0] == a){
            return true;
          }
        }
      }
    }
    //if everything fails it means no winner so return false
    return false;
  }

  //Prints out Winner
  private String wPlayer(String a){
    return "Player "+a+": is the Winner!";
  }

}
