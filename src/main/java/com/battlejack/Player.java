package com.battlejack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private int score;
    private int money;
    private int currentBet;

    public Player(int startingMoney) {
        hand = new ArrayList<>();
        score = 0;
        money = startingMoney;
    }

    public void addCard(Card card) {
        hand.add(card);
        updateScore();
    }

    public void addHiddenCard(Card card){
        hand.add(card);
    }

    public void flipCard(){
        updateScore();
    }

    public boolean dealerBlackjack() {
        // The dealer must have exactly two cards to check for blackjack
        if (hand.size() != 2) {
            return false;
        }
    
        // Calculate the total score for the first two cards
        int total = hand.get(0).getValue() + hand.get(1).getValue();
    
        // If the total is 21, flip the hidden card and return true
        if (total == 21) {
            flipCard(); // Reveal the hidden card
            return true;
        }
    
        return false;
    }

    public void takeTurn(Deck deck){
        // Dealer's turn: hit until score is at least 17
        while (getScore() < 17) {
            addCard(deck.deal());
        }
    }

    private void updateScore() {
        score = 0; // Reset the score
        int aceCount = 0;

        // Calculate the score and count the Aces
        for (Card card : hand) {
            if (card.getRank() == Rank.ACE) {
                score += 11; // Treat Ace as 11 by default
                aceCount++;
            } else {
                score += card.getValue(); // Get value for other cards
            }
        }

        // Adjust Aces from 11 to 1 if the score exceeds 21
        while (score > 21 && aceCount > 0) {
            score -= 10; // Subtract 10 for each Ace adjusted
            aceCount--;  // Reduce the count of Aces treated as 11
        }
    }

    public int getScore() {
        return score;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getMoney() {
        return money;
    }

    public void updateMoney(int amount) {
        money += amount;
    }

    public boolean lost(){
        return money <= 0;
    }

    public boolean bust(){
        return score > 21;
    }

    public void clearHand(){
        hand.clear();
        score = 0;
    }

    public boolean canBet(int betAmount) {
        return betAmount <= money;
    }

    public void placeBet(int betAmount) {
        if (!canBet(betAmount)) {
            throw new IllegalArgumentException("Insufficient funds to place this bet.");
        }
        money -= betAmount;
    }

    public int getCurrentBet(){
        return currentBet;
    }

    public void setCurrentBet(int bet){
        currentBet += bet;
    }
}
