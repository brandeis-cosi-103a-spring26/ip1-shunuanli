package edu.brandeis.cosi103a.ip1;

import java.util.Random;

public class Die {
    private Random random;

    public Die() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1; // Returns a number between 1 and 6
    }
}