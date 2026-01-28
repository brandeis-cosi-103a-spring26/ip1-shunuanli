package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import java.util.List;

/**
 * Unit tests for Supply class.
 * Tests supply initialization, card availability, purchasing, and game end condition.
 */
public class SupplyTest {
    
    private Supply supply;

    @Before
    public void setUp() {
        supply = new Supply();
    }

    /**
     * Test that supply initializes with standard card piles.
     */
    @Test
    public void testSupplyInitialization() {
        assertNotNull("Supply should be initialized", supply);
        assertFalse("Game should not be over at start", supply.isGameOver());
    }

    /**
     * Test that canBuy returns true for available cards.
     */
    @Test
    public void testCanBuyAvailableCard() {
        // Bitcoin should be available at start
        AutomationCard bitcoin = new AutomationCard("Bitcoin", 5, 1);
        assertTrue("Bitcoin should be available at start", supply.canBuy(bitcoin));
    }

    /**
     * Test that canBuy returns false for unavailable cards.
     */
    @Test
    public void testCanBuyUnavailableCard() {
        Card nonexistentCard = new CryptoCard("NonExistent", 0, 0);
        assertFalse("Non-existent card should not be available", supply.canBuy(nonexistentCard));
    }

    /**
     * Test that take removes a card from supply.
     */
    @Test
    public void testTakeReducesCardCount() {
        AutomationCard bitcoin = new AutomationCard("Bitcoin", 5, 1);
        assertTrue("Bitcoin should be available before take", supply.canBuy(bitcoin));
        
        // Take multiple copies
        for (int i = 0; i < 10; i++) {
            supply.take(bitcoin);
        }
        
        // After taking all, should not be available
        assertFalse("Bitcoin should not be available after taking all", supply.canBuy(bitcoin));
    }

    /**
     * Test that take throws exception for unavailable card.
     */
    @Test(expected = IllegalStateException.class)
    public void testTakeThrowsForUnavailableCard() {
        Card nonexistentCard = new CryptoCard("NonExistent", 0, 0);
        supply.take(nonexistentCard);
    }

    /**
     * Test that game end condition is detected when game end card pile is empty.
     */
    @Test
    public void testGameOverWhenEndCardIsEmpty() {
        AutomationCard bitcoin = new AutomationCard("Bitcoin", 5, 1);
        
        // Take all copies of game end card
        while (supply.canBuy(bitcoin)) {
            supply.take(bitcoin);
        }
        
        assertTrue("Game should be over when end card pile is empty", supply.isGameOver());
    }

    /**
     * Test purchasableUpTo returns only affordable cards.
     */
    @Test
    public void testPurchasableUpTo() {
        List<Card> purchasable = supply.purchasableUpTo(5);
        
        assertNotNull("Purchasable list should not be null", purchasable);
        
        // All cards should have cost <= 5
        for (Card card : purchasable) {
            assertTrue("Card cost should be <= coins", card.cost() <= 5);
        }
    }

    /**
     * Test purchasableUpTo with low coins returns fewer cards.
     */
    @Test
    public void testPurchasableUpToLowCoins() {
        List<Card> purchasable = supply.purchasableUpTo(0);
        
        // With 0 coins, should not be able to buy anything
        assertTrue("With 0 coins, should not be able to buy anything", purchasable.isEmpty());
    }

    /**
     * Test isEmpty is consistent with isGameOver.
     */
    @Test
    public void testIsEmptyConsistentWithGameOver() {
        assertEquals("isEmpty should match isGameOver", supply.isEmpty(), supply.isGameOver());
    }
}
