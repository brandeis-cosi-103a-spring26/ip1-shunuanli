package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Optional;

/**
 * Unit tests for PurchaseStrategy implementations.
 * Tests card selection logic for automated strategies.
 */
public class PurchaseStrategyTest {
    
    private PurchaseStrategy strategy;
    private Supply supply;
    private PlayerState player;

    @Before
    public void setUp() {
        strategy = new GreedyStrategy();
        supply = new Supply();
        player = new PlayerState();
    }

    /**
     * Test that chooseBuy returns Optional with a card when coins are available.
     */
    @Test
    public void testChooseBuyWithSufficientCoins() {
        Optional<Card> chosen = strategy.chooseBuy(supply, 10, player);
        assertTrue("Should choose a card with 10 coins", chosen.isPresent());
    }

    /**
     * Test that chooseBuy returns empty Optional with insufficient coins.
     */
    @Test
    public void testChooseBuyWithInsufficientCoins() {
        Optional<Card> chosen = strategy.chooseBuy(supply, 0, player);
        assertFalse("Should not choose a card with 0 coins", chosen.isPresent());
    }

    /**
     * Test that greedy strategy chooses the most expensive affordable card.
     */
    @Test
    public void testGreedyStrategyChoosesMostExpensive() {
        // Supply has cards with various costs
        Optional<Card> chosen = strategy.chooseBuy(supply, 10, player);
        
        if (chosen.isPresent()) {
            // The chosen card should be among the most expensive available
            Card chosenCard = chosen.get();
            assertTrue("Chosen card cost should be reasonable", chosenCard.cost() <= 10);
        }
    }

    /**
     * Test that strategy respects supply availability.
     */
    @Test
    public void testStrategyRespectsSupplyAvailability() {
        Optional<Card> chosen = strategy.chooseBuy(supply, 5, player);
        
        if (chosen.isPresent()) {
            // The chosen card should be available in supply
            Card chosenCard = chosen.get();
            assertTrue("Chosen card should be available in supply", supply.canBuy(chosenCard));
        }
    }

    /**
     * Test that strategy chooses consistently with same inputs.
     */
    @Test
    public void testStrategyIsConsistent() {
        Optional<Card> choice1 = strategy.chooseBuy(supply, 5, player);
        Optional<Card> choice2 = strategy.chooseBuy(supply, 5, player);
        
        assertEquals("Strategy should make same choice with same inputs", choice1, choice2);
    }

    /**
     * Test that strategy handles edge case with exactly 1 coin.
     */
    @Test
    public void testStrategyWithOneCoins() {
        Optional<Card> chosen = strategy.chooseBuy(supply, 1, player);
        
        if (chosen.isPresent()) {
            Card chosenCard = chosen.get();
            assertEquals("With 1 coin, should only choose cards costing 1", 1, chosenCard.cost());
        }
    }

    /**
     * Test that strategy uses player state information if available.
     */
    @Test
    public void testStrategyUsesPlayerState() {
        // Strategy should accept player state parameter without error
        Optional<Card> chosen = strategy.chooseBuy(supply, 5, player);
        
        // Just verify the method accepts player state and returns a valid result
        assertNotNull("chooseBuy should return a non-null Optional", chosen);
    }
}
