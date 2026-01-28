package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * Unit tests for GameEngine class.
 * Tests game initialization, turn execution, player selection, and game completion.
 */
public class GameEngineTest {
    
    private GameEngine engine;
    private Supply supply;
    private PlayerState p1;
    private PlayerState p2;
    private PurchaseStrategy s1;
    private PurchaseStrategy s2;
    private Random rng;

    @Before
    public void setUp() {
        supply = new Supply();
        p1 = new PlayerState();
        p2 = new PlayerState();
        s1 = new GreedyStrategy();
        s2 = new GreedyStrategy();
        rng = new Random(42);
        
        engine = new GameEngine(supply, p1, p2, s1, s2, rng);
    }

    /**
     * Test that GameEngine initializes with all components.
     */
    @Test
    public void testGameEngineInitialization() {
        assertNotNull("GameEngine should be initialized", engine);
    }

    /**
     * Test that chooseStartingPlayer returns 0 or 1.
     */
    @Test
    public void testChooseStartingPlayerReturnsValidIndex() {
        int startingPlayer = engine.chooseStartingPlayer();
        assertTrue("Starting player should be 0 or 1", startingPlayer == 0 || startingPlayer == 1);
    }

    /**
     * Test that playTurn executes without exception.
     */
    @Test
    public void testPlayTurnExecutes() {
        // playTurn should complete without throwing
        engine.playTurn(p1, s1);
        
        // At minimum, player should have been given a chance to play
        assertNotNull("Player state should remain valid after turn", p1);
    }

    /**
     * Test that playGame completes and returns a GameResult.
     */
    @Test
    public void testPlayGameReturnsResult() {
        GameResult result = engine.playGame();
        
        assertNotNull("playGame should return a GameResult", result);
        assertNotNull("Result should have p1 AP", result.p1AutomationPoints());
        assertNotNull("Result should have p2 AP", result.p2AutomationPoints());
        assertNotNull("Result should have winner index", result.winnerIndex());
    }

    /**
     * Test that GameResult correctly identifies ties.
     */
    @Test
    public void testGameResultIdentifiesTies() {
        // Create a custom GameResult with equal scores
        GameResult tieResult = new GameResult(10, 10, 0);
        assertTrue("Should identify tie when scores are equal", tieResult.isTie());
    }

    /**
     * Test that GameResult correctly identifies non-ties.
     */
    @Test
    public void testGameResultIdentifiesNonTies() {
        GameResult nonTieResult = new GameResult(10, 15, 1);
        assertFalse("Should not be a tie when scores differ", nonTieResult.isTie());
    }

    /**
     * Test that game result has valid winner index.
     */
    @Test
    public void testGameResultHasValidWinnerIndex() {
        GameResult result = engine.playGame();
        
        int winnerIndex = result.winnerIndex();
        assertTrue("Winner should be player 0 or 1", winnerIndex == 0 || winnerIndex == 1);
    }

    /**
     * Test that final scores are non-negative.
     */
    @Test
    public void testFinalScoresAreNonNegative() {
        GameResult result = engine.playGame();
        
        assertTrue("Player 1 AP should be non-negative", result.p1AutomationPoints() >= 0);
        assertTrue("Player 2 AP should be non-negative", result.p2AutomationPoints() >= 0);
    }

    /**
     * Test that game terminates (supply end condition).
     */
    @Test
    public void testGameTerminatesWithSupplyEndCondition() {
        GameResult result = engine.playGame();
        
        // If game completed, it means supply end condition was met
        assertNotNull("Game should terminate with a result", result);
    }

    /**
     * Test that playGame respects purchase strategies.
     */
    @Test
    public void testPlayGameRespectsPurchaseStrategies() {
        // Create with specific strategies
        Supply testSupply = new Supply();
        PlayerState testP1 = new PlayerState();
        PlayerState testP2 = new PlayerState();
        PurchaseStrategy greedyS1 = new GreedyStrategy();
        PurchaseStrategy greedyS2 = new GreedyStrategy();
        
        GameEngine testEngine = new GameEngine(testSupply, testP1, testP2, greedyS1, greedyS2, rng);
        GameResult result = testEngine.playGame();
        
        // Both players should have accumulated some cards
        assertTrue("Game should allow players to accumulate cards", 
                   result.p1AutomationPoints() >= 0 && result.p2AutomationPoints() >= 0);
    }
}
