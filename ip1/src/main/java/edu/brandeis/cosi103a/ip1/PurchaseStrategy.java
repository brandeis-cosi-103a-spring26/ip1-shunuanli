package edu.brandeis.cosi103a.ip1;

import java.util.Optional;
import java.util.List;

/**
 * Chooses what card to buy during the buy phase, given coins and current supply.
 */
public interface PurchaseStrategy {

    /**
     * Chooses a card to buy, or chooses not to buy.
     *
     * @param supply shared supply
     * @param coins available coins
     * @param me current player's state (optional use for smarter strategies)
     * @return the chosen card to buy, or empty if buying nothing
     */
    Optional<Card> chooseBuy(Supply supply, int coins, PlayerState me);
}

/**
 * Simple automated strategy that makes a deterministic purchase choice.
 */
final class GreedyStrategy implements PurchaseStrategy {

    /**
     * Chooses the "best" affordable card based on a simple rule.
     *
     * @param supply shared supply
     * @param coins available coins
     * @param me current player's state
     * @return chosen card, or empty if none can be afforded
     */
    @Override
    public Optional<Card> chooseBuy(Supply supply, int coins, PlayerState me) {
        // Get all purchasable cards with available coins
        List<Card> purchasable = supply.purchasableUpTo(coins);
        
        // If no cards can be purchased, return empty
        if (purchasable.isEmpty()) {
            return Optional.empty();
        }
        
        // Greedy strategy: choose the most expensive card available
        Card bestCard = purchasable.get(0);
        for (Card card : purchasable) {
            if (card.cost() > bestCard.cost()) {
                bestCard = card;
            }
        }
        
        return Optional.of(bestCard);
    }
}
