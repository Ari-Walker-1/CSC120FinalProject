import java.util.Scanner;

public class Player {

  public String name;
  public char letter;
  public int gamesWon;
  public int gamesTied;
  public boolean turn;

  /**
   * Player Constructor
   * @param name
   * @param letter
   */
  public Player(String name, char letter) {
    this.name = name;
    this.letter = letter;
    gamesWon = 0;
    gamesTied = 0;
    turn = false;
  }

  /**
   * Switches which turn the player is going (called in TicTacToeGame after each game)
   */
  public void switchTurn() {
    if (turn) {
      turn = false;
    } else {
      turn = true;
    }
  }

  /**
   * Switches the letter that the player is playing as (called in TicTacToeGame after each game)
   */
  public void switchLetter() {
    if (letter == 'X') {
      letter = 'O';
    } else {
      letter = 'X';
    }
  }

  /**
   * Welcomes and tells the player their assigned letter
   */
  public void assignLetter() {
    System.out.println("Welcome " + name + "! You are playing as: " + letter);
  }

  public static void main(String[] args) {}
}
