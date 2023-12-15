import java.util.Scanner;

public class TicTacToeGame {

  char board[][];
  Scanner scan = new Scanner(System.in);

  /**
   * TicTacToeGame Constructor
   */
  public TicTacToeGame() {
    board = new char[3][3];
    this.printBoard();
  }

  /**
   * Method that identifies if a spot on the board is full
   * @param row
   * @param col
   * @return a boolean value for if the spot is full
   */
  public boolean spotFull(int row, int col) {
    if (this.board[row][col] == 'X') {
      return true;
    }
    if (this.board[row][col] == 'O') {
      return true;
    }
    return false;
  }

  /**
   * Method that ends the game if a player has either won or both players have tied
   * @return a boolean value for if the game is over
   */
  public boolean gameOver() {

    // checks horizontal wins, makes sure all 3 are the same and not null
    if (this.board[0][0] == this.board[0][1] && this.board[0][0] == this.board[0][2] && this.spotFull(0, 0)) {
      return true;
    }
    if (this.board[1][0] == this.board[1][1] && this.board[1][0] == this.board[1][2] && this.spotFull(1, 0)) {
      return true;
    }
    if (this.board[2][0] == this.board[2][1] && this.board[2][0] == this.board[2][2] && this.spotFull(2, 0)) {
      return true;
    }

    //checks vertical wins, makes sure all 3 are the same and not null
    if (this.board[0][0] == this.board[1][0] && this.board[1][0] == this.board[2][0] && this.spotFull(0, 0)) {
      return true;
    }
    if (this.board[0][1] == this.board[1][1] && this.board[1][1] == this.board[2][1] && this.spotFull(0, 1)) {
      return true;
    }
    if (this.board[0][2] == this.board[1][2] && this.board[1][2] == this.board[2][2] && this.spotFull(0, 2)) {
      return true;
    }

    // checks diagonal wins, makes sure all 3 are the same and not null
    if (this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2] && this.spotFull(0, 0)) {
      return true;
    }
    if (this.board[2][0] == this.board[1][1] && this.board[2][0] == this.board[0][2] && this.spotFull(2, 0)) {
      return true;
    }

    // all the win cases have been checked, therefore no one has won
    return false;
  }

  /**
   * Method that tells if all the spots on the board have been used
   * @return a boolean value for if the board is full
   */
  public boolean boardFull() {
    // looks through each element in board. It it's not full, then board isn't full. Otherwise, it is.
    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board[i].length; j++) {
        if (!this.spotFull(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Method that places a player's letter in a spot on the board based on the parameters later assigned in main
   * @param row
   * @param col
   * @param i
   */
  public void placeSpot(int row, int col, char i) {
    this.board[row][col] = i;
  }

  /**
   * prints the player's scores at the end of the round
   * @param firstPlayer
   * @param secondPlayer
   */
  public void printScore(Player firstPlayer, Player secondPlayer) {
    if (firstPlayer.turn) {
      secondPlayer.gamesWon++;
      System.out.println("Congratulations on the win, " + secondPlayer.name + "!");
    } else {
      firstPlayer.gamesWon++;
      System.out.println("Congratulations on the win, " + firstPlayer.name + "!");
    }
  }

  /**
   * Method that lets the player input a move
   * @param p
   */
  public void turn(Player p) {
    boolean acceptableInput = false;

    // keeps going until a valid input has been given
    while (!acceptableInput) {

      boolean acceptableRow = false;
      boolean acceptableCol = false;

      int moveRow = -1;
      int moveCol = -1;

      while (!acceptableRow) {
        System.out.println("What row do you want your move to be placed in (1-3), " + p.name + "?");
        try {
          moveRow = Integer.parseInt(scan.nextLine()) - 1;
          acceptableRow = true;

          if (moveRow > 2 || moveRow < 0) {
            acceptableRow = false;
            System.out.println("Your input was not between 1 and 3. Please try again.");
          }
        } catch (Exception e) {
          System.out.println("You did not enter an integer value, please try again.");
        }
      }

      while (!acceptableCol) {
        System.out.println("What column do you want your move to be placed in (1-3)?");
        try {
          moveCol = Integer.parseInt(scan.nextLine()) - 1;
          acceptableCol = true;

          if (moveCol > 2 || moveCol < 0) {
            acceptableCol = false;
            System.out.println("Your input was not between 1 and 3. Please try again.");
          }
        } catch (Exception e) {
          System.out.println("You did not enter an integer value, please try again.");
        }
      }

      // checks to make sure no one has placed something in that spot yet. If it has, it loops. If not, it places the move and exits the loop.
      if (this.spotFull(moveRow, moveCol)) {
        System.out.println("This spot is full. Please input an empty spot.");
      } else {
        acceptableInput = true;
        this.placeSpot(moveRow, moveCol, p.letter);
      }
    }
  }

  /**
   * Method that prints/creates an empty board
   */
  public void printBoard() {
    System.out.println();

    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board[i].length; j++) {
        System.out.print(" " + board[i][j]);
        // if the element is a space, an extra space is added to stay consistent with space taken up by alphabetic characters
        if (!this.spotFull(i, j)) {
          System.out.print(" ");
        }
        if (j == 0 || j == 1) {
          System.out.print(" |");
        }
        if (j == 2 && i != 2) {
          System.out.println();
          System.out.println("-----------");
        }
      }
    }
    System.out.println("\n");
  }

  public static void main(String[] args) {
    // Getting user input for the players and creating instances of them

    Scanner scan = new Scanner(System.in);

    System.out.println("Player #1, what is your name?");
    String name1 = scan.nextLine();
    Player firstPlayer = new Player(name1, 'X');
    firstPlayer.assignLetter();
    firstPlayer.turn = true;

    System.out.println("Player #2, what is your name?");
    String name2 = scan.nextLine();
    Player secondPlayer = new Player(name2, 'O');
    secondPlayer.assignLetter();

    // Getting user input for the number of rounds and creating an int totRounds, catching an exception if it isn't an int
    System.out.println("How many rounds would you like to play?");
    int totRounds = Integer.parseInt(scan.nextLine());
    System.out.println("You have chosen to play " + totRounds + " rounds.");

    for (int i = 0; i < totRounds; i++) {
      int games = i + 1;
      System.out.println("Game " + games + " has been started.");
      TicTacToeGame game = new TicTacToeGame();
      if (firstPlayer.letter == 'X') {
        firstPlayer.turn = true;
        secondPlayer.turn = false;
      } else {
        firstPlayer.turn = false;
        secondPlayer.turn = true;
      }

      while (!game.gameOver() && !game.boardFull()) {

        if (firstPlayer.turn) {
          game.turn(firstPlayer);
        } else {
          game.turn(secondPlayer);
        }
        firstPlayer.switchTurn();
        secondPlayer.switchTurn();
        game.printBoard();
      }

      if (game.gameOver()) {
        game.printScore(firstPlayer, secondPlayer);
      }

      if (game.boardFull()) {
        System.out.println("It's a tie.");
        firstPlayer.gamesTied++;
        secondPlayer.gamesTied++;
      }

      firstPlayer.switchLetter();
      secondPlayer.switchLetter();
    }

    if (firstPlayer.gamesWon > secondPlayer.gamesWon) {
      System.out.println(firstPlayer.name + " won most games overall, with " + firstPlayer.gamesWon + " total wins.");
      System.out.println("Better luck next time, " + secondPlayer.name + "! You weren't far behind with " + secondPlayer.gamesWon + " wins.");
    } else if (firstPlayer.gamesWon < secondPlayer.gamesWon) {
      System.out.println(secondPlayer.name + " won most games overall, with " + secondPlayer.gamesWon + " total wins.");
      System.out.println("Better luck next time, " + firstPlayer.name + "! You weren't far behind with " + firstPlayer.gamesWon + " wins.");
    } else {
      System.out.println("Both " + firstPlayer.name + " and " + secondPlayer.name + " tied with " + firstPlayer.gamesWon + " wins!");
    }
    scan.close();
  }
}
