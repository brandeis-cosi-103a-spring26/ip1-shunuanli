package edu.brandeis.cosi103a.ip1;

import java.util.Random;

/**
 * Entry point for IP2: runs a complete game between two automated players.
 *
 * <p>Creates the supply, initializes both players with starter decks, selects a random
 * starting player, runs the game loop until the supply end condition is met, and
 * prints the result.
 */
public final class App {

    /**
     * Runs the program.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        // Create a random number generator for starting player selection and shuffling
        Random rng = new Random();
        
        // Create the shared supply
        Supply supply = new Supply();
        
        // Initialize both players with starter decks
        PlayerState player1 = new PlayerState();
        PlayerState player2 = new PlayerState();
        
        // Create purchase strategies for both players (using GreedyStrategy)
        PurchaseStrategy strategy1 = new GreedyStrategy();
        PurchaseStrategy strategy2 = new GreedyStrategy();
        
        // Create the game engine
        GameEngine engine = new GameEngine(supply, player1, player2, strategy1, strategy2, rng);
        
        // Play the game until supply end condition is met
        GameResult result = engine.playGame();
        
        // Print the result
        System.out.println("Game Over!");
        System.out.println("Player 1 Automation Points: " + result.p1AutomationPoints());
        System.out.println("Player 2 Automation Points: " + result.p2AutomationPoints());
        
        if (result.isTie()) {
            System.out.println("Result: Tie");
        } else {
            int winner = result.winnerIndex() + 1; // Convert 0-indexed to 1-indexed
            System.out.println("Winner: Player " + winner);
        }
    }
}
