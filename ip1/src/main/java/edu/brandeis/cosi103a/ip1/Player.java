package edu.brandeis.cosi103a.ip1;

import java.util.Scanner;

public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public int takeTurn() {
        Die die = new Die();
        int totalRoll = 0;
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < 10; i++) {
            int roll = die.roll();
            System.out.println(name + " rolled: " + roll);
            totalRoll += roll;

            System.out.println("Do you want to re-roll? (yes/no)");
            String response = scanner.nextLine();
            int rerolls = 0;

            while (response.equalsIgnoreCase("yes") && rerolls < 2) {
                roll = die.roll();
                System.out.println(name + " re-rolled: " + roll);
                totalRoll += roll;
                rerolls++;
                System.out.println("Do you want to re-roll again? (yes/no)");
                response = scanner.nextLine();
            }

            System.out.println(name + " ends turn with total roll: " + totalRoll);
            addScore(totalRoll);
            totalRoll = 0; // Reset for the next turn
        }
        return getScore();
    }
}