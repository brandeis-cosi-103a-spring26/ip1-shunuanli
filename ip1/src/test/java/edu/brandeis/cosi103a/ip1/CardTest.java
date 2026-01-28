package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit tests for Card implementations.
 * Tests AutomationCard and CryptoCard functionality.
 */
public class CardTest {
    
    /**
     * Test that AutomationCard has correct name.
     */
    @Test
    public void testAutomationCardName() {
        AutomationCard card = new AutomationCard("Bitcoin", 5, 1);
        assertEquals("Card name should be Bitcoin", "Bitcoin", card.name());
    }

    /**
     * Test that AutomationCard has correct cost.
     */
    @Test
    public void testAutomationCardCost() {
        AutomationCard card = new AutomationCard("Bitcoin", 5, 1);
        assertEquals("Card cost should be 5", 5, card.cost());
    }

    /**
     * Test that AutomationCard has correct AP value.
     */
    @Test
    public void testAutomationCardAPValue() {
        AutomationCard card = new AutomationCard("Bitcoin", 5, 1);
        assertEquals("Card AP value should be 1", 1, card.apValue());
    }

    /**
     * Test that AutomationCard with different AP value works correctly.
     */
    @Test
    public void testAutomationCardWithHighAPValue() {
        AutomationCard card = new AutomationCard("Premium", 8, 5);
        assertEquals("Card AP value should be 5", 5, card.apValue());
    }

    /**
     * Test that CryptoCard has correct name.
     */
    @Test
    public void testCryptoCardName() {
        CryptoCard card = new CryptoCard("Ethereum", 4, 3);
        assertEquals("Card name should be Ethereum", "Ethereum", card.name());
    }

    /**
     * Test that CryptoCard has correct cost.
     */
    @Test
    public void testCryptoCardCost() {
        CryptoCard card = new CryptoCard("Ethereum", 4, 3);
        assertEquals("Card cost should be 4", 4, card.cost());
    }

    /**
     * Test that CryptoCard has correct coin value.
     */
    @Test
    public void testCryptoCardCoinValue() {
        CryptoCard card = new CryptoCard("Ethereum", 4, 3);
        assertEquals("Card coin value should be 3", 3, card.coinValue());
    }

    /**
     * Test that CryptoCard with different coin value works correctly.
     */
    @Test
    public void testCryptoCardWithHighCoinValue() {
        CryptoCard card = new CryptoCard("Bitcoin", 3, 2);
        assertEquals("Card coin value should be 2", 2, card.coinValue());
    }

    /**
     * Test that AutomationCard and CryptoCard can be compared by cost.
     */
    @Test
    public void testCardCostComparison() {
        AutomationCard automation = new AutomationCard("Method", 6, 2);
        CryptoCard crypto = new CryptoCard("Ethereum", 8, 3);
        
        assertTrue("Automation card should cost less than crypto", 
                   automation.cost() < crypto.cost());
    }

    /**
     * Test that card with cost 0 is valid.
     */
    @Test
    public void testCardWithZeroCost() {
        AutomationCard card = new AutomationCard("Free", 0, 1);
        assertEquals("Card cost should be 0", 0, card.cost());
    }

    /**
     * Test that Card interface is properly implemented.
     */
    @Test
    public void testCardInterfaceImplementation() {
        Card automation = new AutomationCard("Test", 5, 1);
        Card crypto = new CryptoCard("Test", 5, 1);
        
        assertNotNull("AutomationCard should implement Card", automation);
        assertNotNull("CryptoCard should implement Card", crypto);
        assertTrue("AutomationCard should have name", automation.name().length() > 0);
        assertTrue("CryptoCard should have name", crypto.name().length() > 0);
    }

    /**
     * Test that multiple cards with same properties are distinct.
     */
    @Test
    public void testMultipleCardsWithSameProperties() {
        AutomationCard card1 = new AutomationCard("Bitcoin", 5, 1);
        AutomationCard card2 = new AutomationCard("Bitcoin", 5, 1);
        
        // They should have same properties
        assertEquals("Same card name", card1.name(), card2.name());
        assertEquals("Same card cost", card1.cost(), card2.cost());
        assertEquals("Same AP value", card1.apValue(), card2.apValue());
    }
}
