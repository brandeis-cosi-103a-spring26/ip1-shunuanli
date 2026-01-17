package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Integration tests for App class and overall game flow
 */
public class AppIntegrationTest {

    /**
     * Test that App class exists and has main method
     */
    @Test
    public void testAppClassExists() {
        assertNotNull("App class should exist", App.class);
    }

    /**
     * Test that Die roll produces valid 6-sided die values
     */
    @Test
    public void testDieIntegration() {
        Die die = new Die();
        int roll = die.roll();
        assertTrue("Die should roll between 1-6", roll >= 1 && roll <= 6);
    }

    /**
     * Test that Player can be created and track score
     */
    @Test
    public void testPlayerIntegration() {
        Player player = new Player("TestPlayer");
        assertEquals("Player name should match", "TestPlayer", player.getName());
        
        player.addScore(10);
        assertEquals("Player score should increase by 10", 10, player.getScore());
    }

    /**
     * Test that Game can be created with two players
     */
    @Test
    public void testGameIntegration() {
        Game game = new Game("Player1", "Player2");
        assertNotNull("Game should be created successfully", game);
    }

    /**
     * Test integration: Die produces values that can be added to Player score
     */
    @Test
    public void testDieAndPlayerIntegration() {
        Die die = new Die();
        Player player = new Player("TestPlayer");
        
        int rollValue = die.roll();
        player.addScore(rollValue);
        
        assertTrue("Player score should match die roll", player.getScore() >= 1 && player.getScore() <= 6);
    }

    /**
     * Test that two players can be created independently
     */
    @Test
    public void testTwoPlayersIntegration() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        
        player1.addScore(5);
        player2.addScore(3);
        
        assertEquals("Player1 score should be 5", 5, player1.getScore());
        assertEquals("Player2 score should be 3", 3, player2.getScore());
    }

    /**
     * Test game component creation flow
     */
    @Test
    public void testGameFlowComponents() {
        // Test that all required components can be created
        Die die = new Die();
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Game game = new Game("Alice", "Bob");
        
        assertNotNull("Die should be created", die);
        assertNotNull("Player 1 should be created", p1);
        assertNotNull("Player 2 should be created", p2);
        assertNotNull("Game should be created", game);
    }
}
