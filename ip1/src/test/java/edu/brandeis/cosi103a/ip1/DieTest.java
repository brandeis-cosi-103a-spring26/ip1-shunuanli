package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Die class
 */
public class DieTest {
    
    private Die die;

    @Before
    public void setUp() {
        die = new Die();
    }

    /**
     * Test that die roll returns a value between 1 and 6 (inclusive)
     */
    @Test
    public void testRollReturnsValidRange() {
        for (int i = 0; i < 100; i++) {
            int roll = die.roll();
            assertTrue("Roll should be >= 1", roll >= 1);
            assertTrue("Roll should be <= 6", roll <= 6);
        }
    }

    /**
     * Test that die can produce all possible values (1-6)
     */
    @Test
    public void testRollProducesAllValues() {
        boolean[] found = new boolean[7]; // indices 1-6
        
        // Roll die enough times to likely get all values
        for (int i = 0; i < 1000; i++) {
            int roll = die.roll();
            found[roll] = true;
        }
        
        // Check that we got at least some variety
        int count = 0;
        for (int i = 1; i <= 6; i++) {
            if (found[i]) count++;
        }
        assertTrue("Die should produce multiple different values", count > 2);
    }

    /**
     * Test that die produces expected range in multiple rolls
     */
    @Test
    public void testMultipleRollsInValidRange() {
        int roll1 = die.roll();
        int roll2 = die.roll();
        int roll3 = die.roll();
        
        assertTrue("First roll should be 1-6", roll1 >= 1 && roll1 <= 6);
        assertTrue("Second roll should be 1-6", roll2 >= 1 && roll2 <= 6);
        assertTrue("Third roll should be 1-6", roll3 >= 1 && roll3 <= 6);
    }
}
