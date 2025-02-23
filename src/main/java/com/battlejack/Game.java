package com.battlejack;

import java.util.List;

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;
    private boolean gameOver;
    private boolean playerLost;
    private String winnerMessage;
    private String moneyText;
    private int goal;
    private int winnings;

    public Game() {
        deck = new Deck(6);
        player = new Player(GameState.getStartingMoney());
        dealer = new Player(GameState.getWinningTarget());
        goal = GameState.getWinningTarget();
        winnings = 0;
        gameOver = false;
        winnerMessage = "";
        moneyText = Integer.toString(player.getMoney());
    }

    public void startNewHand() {
        player.clearHand();
        dealer.clearHand();
        gameOver = false;
        winnerMessage = "Set your bet amount";

        // Shuffle a new shoe if cards are too low
        if (deck.getRemainingCards() < 78) {
            deck = new Deck(6);
        }
        
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addHiddenCard(deck.deal());

        // Check for Blackjack
        if (player.getScore() == 21 && dealer.dealerBlackjack()) {
            player.setCurrentBet(-player.getCurrentBet());
            winnerMessage = "Push! Both Player and Dealer have Blackjack.";
            gameOver = true;
        } else if (player.getScore() == 21 && player.getHand().size() == 2) {
            GameState.addWinnings(player.getCurrentBet() * 2 * GameState.getMultiplier());
            player.updateMoney(player.getCurrentBet() * 3 * GameState.getMultiplier());
            updateWinnings(player.getCurrentBet() * 2 * GameState.getMultiplier());
            player.setCurrentBet(-player.getCurrentBet());
            moneyText = Integer.toString(player.getMoney());
            winnerMessage = "Player wins with a Blackjack!";
            gameOver = true;
        } else if (dealer.dealerBlackjack()) {
            player.setCurrentBet(-player.getCurrentBet());
            winnerMessage = "Dealer wins with a Blackjack!";
            gameOver = true;
        }
    }

    public void playerHit() {
        player.addCard(deck.deal());
        if (player.getScore() > 21) {
            player.setCurrentBet(-player.getCurrentBet());
            winnerMessage = "Bust! Player loses.";
            gameOver = true;
        }
        if (player.getMoney() == 0) {
            playerLost = true;
        }
    }

    public void playerStand() {
        dealer.flipCard();

        dealer.takeTurn(deck);

        // Determine the winner
        determineWinner();
        gameOver = true;
    }

    public List<Card> getPlayerHand() {
        return player.getHand();
    }

    public List<Card> getDealerHand(boolean revealAll) {
        if (revealAll) {
            return dealer.getHand();
        } else {
            return dealer.getHand().subList(0, 1);
        }
    }

    public int getPlayerMoney(){
        return player.getMoney();
    }

    public boolean canDouble(){
        return player.getMoney() > player.getCurrentBet();
    }

    public void playerDoubleDown(){
        player.updateMoney(-player.getCurrentBet());
        player.setCurrentBet(player.getCurrentBet());
        player.addCard(deck.deal());
        if (player.getScore() > 21) {
            player.setCurrentBet(-player.getCurrentBet());
            winnerMessage = "Bust! Player loses.";
            gameOver = true;
            if (player.getMoney() == 0) {
                playerLost = true;
            }
        } else {
            playerStand();
        }
        moneyText = Integer.toString(player.getMoney());
    }

    public void setBet(int amount){
        if(player.canBet(amount)){
            player.updateMoney(-amount);
            player.setCurrentBet(amount);
            winnerMessage = "You bet $" + amount;
            moneyText = Integer.toString(player.getMoney());
        } else {
            winnerMessage = "You cannot bet that amount";
        }
    }

    public void updateWinnings(int amount){
        winnings += amount;
    }

    public int getWinnings(){
        return winnings;
    }

    public int getGoal(){
        return goal;
    }

    void determineWinner() {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (dealerScore > 21) {
            player.updateMoney(player.getCurrentBet() * 2 * GameState.getMultiplier());
            GameState.addWinnings(player.getCurrentBet() * GameState.getMultiplier());
            updateWinnings(player.getCurrentBet() * GameState.getMultiplier());
            player.setCurrentBet(-player.getCurrentBet());
            moneyText = Integer.toString(player.getMoney());
            winnerMessage = "Dealer busts! Player wins!";
        } else if (playerScore > dealerScore) {
            player.updateMoney(player.getCurrentBet() * 2 * GameState.getMultiplier());
            GameState.addWinnings(player.getCurrentBet() * GameState.getMultiplier());
            updateWinnings(player.getCurrentBet() * GameState.getMultiplier());
            player.setCurrentBet(-player.getCurrentBet());
            moneyText = Integer.toString(player.getMoney());
            winnerMessage = "Player wins!";
        } else if (playerScore < dealerScore) {
            if (player.getMoney() == 0) {
                playerLost = true;
            }
            if(playerLost()){
                player.setCurrentBet(-player.getCurrentBet());
                winnerMessage = "Dealer wins! GAME OVER!";
            } else {
                player.setCurrentBet(-player.getCurrentBet());
                winnerMessage = "Dealer wins!";
            }
        } else {
            player.updateMoney(player.getCurrentBet());
            player.setCurrentBet(-player.getCurrentBet());
            moneyText = Integer.toString(player.getMoney());
            winnerMessage = "It's a push!";
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean playerLost(){
        return playerLost;
    }

    public String getWinnerMessage() {
        return winnerMessage;
    }

    public String getMoneyText(){
        return moneyText;
    }

    public boolean validBet(){
        if(player.getMoney() <= 0){
            winnerMessage = "You cannot bet that amount";
        }
        return player.getMoney() > 0;
    }

    public String getPlayerBet(){
        return Integer.toString(player.getCurrentBet());
    }

    public int getDealerScore() {
        return dealer.getScore();
    }

    public int getPlayerScore() {
        return player.getScore();
    }
}

