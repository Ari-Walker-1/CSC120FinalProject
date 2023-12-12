import java.util.Scanner;

public class Player {

    public String name;
    public char letter;
    public int gamesWon;
    public int gamesTied;
    public boolean turn;

    public Player(String name, char letter) {
        this.name = name;
        this.letter = letter;
        gamesWon = 0;
        gamesTied = 0;
        turn = false;
    }

    public void updateScore() {
        ///if X wins (update the score of the player playing X)
        //if O wins (update the score of the player playing O)
    }

    public void printScore() {
        System.out.println(this.name + "Wins: " + this.gamesWon);
        System.out.println(this.name + "Wins: " + this.gamesWon);
        System.out.println("Games Tied: " + gamesTied);
    }


    public void switchTurn() {
        if(turn) {
            turn=false;
        }
        else {
            turn=true;
        }
    }

    public void switchLetter() {
        if(letter=='X') {
            letter='O';
        }
        else {
            letter='X';
        }
    }

    public void assignLetter() {
        System.out.println("Welcome " + name + "! You are playing as: " + letter);
    }

    public static void main(String[] args) {

        Player player1 = new Player(" ", 'C');
        Player player2 = new Player(" ", 'C');
    }
    
}