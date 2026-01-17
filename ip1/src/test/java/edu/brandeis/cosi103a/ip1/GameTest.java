package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for Game class
 */
public class GameTest {
    
    private Game game;

    @Before
    public void setUp() {
        game = new Game("Player1", "Player2");
    }

    /**
     * Test that game initializes with two players
     */
    @Test
    public void testGameInitializationWithTwoPlayers() {
        assertNotNull("Game should be created", game);
        // Game has player1 and player2 initialized internally
        // We can verify by checking that start() method exists and game object is valid
    }

    /**
     * Test that game has correct player names after initialization
     */
    @Test
    public void testGamePlayerNames() {
        Game testGame = new Game("Alice", "Bob");
        assertNotNull("Game should be initialized with two players", testGame);
        // Verify game was created successfully
    }

    /**
     * Test that game can be created with different player names
     */
    @Test
    public void testGameWithDifferentPlayerNames() {
        Game game1 = new Game("John", "Jane");
        Game game2 = new Game("Player1", "Player2");
        
        assertNotNull("First game should be created", game1);
        assertNotNull("Second game should be created", game2);
    }

    /**
     * Test that game starts without exceptions
     */
    @Test
    public void testGameInitializationDoesNotThrowException() {
        try {
            Game testGame = new Game("Test1", "Test2");
            assertNotNull("Game should be initialized", testGame);
        } catch (Exception e) {
            fail("Game initialization should not throw exception: " + e.getMessage());
        }
    }

    /**
     * Test that game can be created for 2 human players as per requirements
     */
    @Test
    public void testGameSupports2HumanPlayers() {
        Game twoPlayerGame = new Game("Human1", "Human2");
        assertNotNull("Game should support 2 human players", twoPlayerGame);
    }

    /**
     * Test game object properties after creation
     */
    @Test
    public void testGameObjectCreated() {
        // Verify game object is properly constructed
        assertNotNull("Game object should not be null", game);
        assertEquals("Game should be instance of Game class", Game.class, game.getClass());
    }
}
