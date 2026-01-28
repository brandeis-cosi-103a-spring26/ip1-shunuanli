package edu.brandeis.cosi103a.ip1;

import java.util.Random;

/**
 * Runs the game loop and enforces IP2 rules for turn order, phases, and end condition.
 */
public final class GameEngine {
    private final Supply supply;
    private final PlayerState p1;
    private final PlayerState p2;
    private final PurchaseStrategy s1;
    private final PurchaseStrategy s2;
    private final Random rng;

    /**
     * Constructs a game engine.
     *
     * @param supply shared supply
     * @param p1 first player's state
     * @param p2 second player's state
     * @param s1 first player's purchase strategy
     * @param s2 second player's purchase strategy
     * @param rng randomness source for shuffling and starting player choice
     */
    public GameEngine(Supply supply, PlayerState p1, PlayerState p2,
                      PurchaseStrategy s1, PurchaseStrategy s2,
                      Random rng) {
        this.supply = supply;
        this.p1 = p1;
        this.p2 = p2;
        this.s1 = s1;
        this.s2 = s2;
        this.rng = rng;
    }

    /**
     * Plays a full game until the end condition is met.
     *
     * @return game result including winner and final scores
     */
    public GameResult playGame() {
        // Choose starting player
        int currentPlayer = chooseStartingPlayer();
        
        // Play turns until supply is empty
        while (!supply.isGameOver()) {
            if (currentPlayer == 0) {
                playTurn(p1, s1);
            } else {
                playTurn(p2, s2);
            }
            
            // Alternate players
            currentPlayer = 1 - currentPlayer;
        }
        
        // Calculate final scores
        int p1AP = p1.totalAutomationPoints();
        int p2AP = p2.totalAutomationPoints();
        
        // Determine winner
        int winnerIndex;
        if (p1AP > p2AP) {
            winnerIndex = 0;
        } else if (p2AP > p1AP) {
            winnerIndex = 1;
        } else {
            // Tie: choose player 0 by default
            winnerIndex = 0;
        }
        
        return new GameResult(p1AP, p2AP, winnerIndex);
    }

    /**
     * Plays exactly one player's turn: buy phase then cleanup phase.
     *
     * @param player current player's state
     * @param strategy purchase strategy for this player
     */
    public void playTurn(PlayerState player, PurchaseStrategy strategy) {
        // Draw phase: draw 5 cards
        player.draw(5, rng);
        
        // Play phase: play all crypto cards to coins
        int coins = player.playAllCryptoToCoins();
        
        // Buy phase: use coins to buy a card
        java.util.Optional<Card> chosenCard = strategy.chooseBuy(supply, coins, player);
        if (chosenCard.isPresent()) {
            Card card = chosenCard.get();
            supply.take(card);
            player.gain(card);
        }
        
        // Cleanup phase: discard all cards and draw new hand
        player.cleanupAndRedraw(rng);
    }

    /**
     * Chooses the starting player index.
     *
     * @return 0 if player 1 starts, 1 if player 2 starts
     */
    public int chooseStartingPlayer() {
        return rng.nextInt(2);
    }
}

/**
 * Immutable summary of a completed game.
 */
record GameResult(
        int p1AutomationPoints,
        int p2AutomationPoints,
        int winnerIndex
) {
    /**
     * @return true if the game ended in a tie
     */
    public boolean isTie() {
        return p1AutomationPoints == p2AutomationPoints;
    }
}
