package edu.brandeis.cosi103a.ip1;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;

    public Game(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
    }

    public void start() {
        System.out.println("Starting the game!");
        player1.takeTurn();
        player2.takeTurn();
        determineWinner();
    }

    private void determineWinner() {
        System.out.println("Final Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());

        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.getName() + " wins!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}