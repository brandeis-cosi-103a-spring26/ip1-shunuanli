package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Unit tests for Player class
 */
public class PlayerTest {
    
    private Player player;

    @Before
    public void setUp() {
        player = new Player("TestPlayer");
    }

    /**
     * Test that player is initialized with correct name and zero score
     */
    @Test
    public void testPlayerInitialization() {
        assertEquals("Player name should be TestPlayer", "TestPlayer", player.getName());
        assertEquals("Initial score should be 0", 0, player.getScore());
    }

    /**
     * Test that addScore correctly accumulates points
     */
    @Test
    public void testAddScoreSingleValue() {
        player.addScore(5);
        assertEquals("Score should be 5 after adding 5", 5, player.getScore());
    }

    /**
     * Test that addScore accumulates multiple values correctly
     */
    @Test
    public void testAddScoreMultipleValues() {
        player.addScore(3);
        player.addScore(4);
        player.addScore(2);
        assertEquals("Score should be 9 after adding 3+4+2", 9, player.getScore());
    }

    /**
     * Test that player score increases after multiple additions
     */
    @Test
    public void testScoreAccumulation() {
        int initialScore = player.getScore();
        for (int i = 1; i <= 5; i++) {
            player.addScore(i);
        }
        assertEquals("Score should be 15 after adding 1+2+3+4+5", 15, player.getScore());
    }

    /**
     * Test that player can achieve scores consistent with 10 turns of dice rolls
     */
    @Test
    public void testScoreRangeAfterMultipleTurns() {
        // Simulate realistic score after several turns
        // Each turn could have die rolls between 1-6 per roll
        for (int turn = 0; turn < 5; turn++) {
            player.addScore(3); // Simulate a die roll
        }
        assertTrue("Score should be positive after turns", player.getScore() > 0);
        assertEquals("Score should be 15 after 5 turns of adding 3", 15, player.getScore());
    }

    /**
     * Test that player name is correctly retrieved
     */
    @Test
    public void testGetPlayerName() {
        Player player2 = new Player("Alice");
        assertEquals("Player name should be Alice", "Alice", player2.getName());
    }

    /**
     * Test that multiple players can have different names and scores
     */
    @Test
    public void testMultiplePlayersIndependent() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        
        player1.addScore(10);
        player2.addScore(5);
        
        assertEquals("Player1 score should be 10", 10, player1.getScore());
        assertEquals("Player2 score should be 5", 5, player2.getScore());
        assertEquals("Player1 name should be Player1", "Player1", player1.getName());
        assertEquals("Player2 name should be Player2", "Player2", player2.getName());
    }
}
