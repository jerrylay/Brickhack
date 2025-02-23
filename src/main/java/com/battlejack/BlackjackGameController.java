package com.battlejack;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class BlackjackGameController {
    @FXML private HBox dealerHandBox, playerHandBox;
    @FXML private Text moneyText, gameStatus, dealerScore, playerScore, winnings, goal;
    @FXML private Button betButton, dealButton, hitButton, standButton, doubleDown;

    private Game game;

    public void initialize() {
        Platform.runLater(() -> {
            game = new Game();
            winnings.setText(String.valueOf(game.getWinnings()));
            goal.setText(String.valueOf(game.getGoal()));
            moneyText.setText(game.getMoneyText());
            preGame();
        });
    }

    @FXML
    private void handleBet() {
        if (game.validBet()) {
            game.setBet(100);
            moneyText.setText(game.getMoneyText());
            startGame();
        }
        gameStatus.setText(game.getWinnerMessage());
    }

    @FXML
    private void handleDeal() {
        game.startNewHand();
        updateUI(false);
        if (game.isGameOver()) {
            preGame();
        } else {
            if(game.canDouble()){
                canDoubleButton();
            } else {
                dealGame();
            }
            gameStatus.setText("");
        }
    }

    @FXML
    private void handleHit() {
        dealGame();
        SoundManager.playSlide();
        game.playerHit();
        updateUI(false);
        if (game.isGameOver() && game.playerLost()) {
            endGame();
            SceneManager.switchScene("MainMenu.fxml");
        } else if (game.isGameOver()) {
            preGame();
        }
    }

    @FXML
    private void handleStand() {
        game.playerStand();
        updateUI(true);
        preGame();
        if (game.isGameOver() && game.playerLost()) {
            endGame();
            SceneManager.switchScene("MainMenu.fxml");
        }
    }

    @FXML
    private void handleDouble(){
        game.playerDoubleDown();
        updateUI(true);
        preGame();
        if (game.isGameOver() && game.playerLost()) {
            endGame();
            SceneManager.switchScene("MainMenu.fxml");
        }
    }

    @FXML
    private void updateDealerScore() {
        dealerScore.setText(String.valueOf(game.getDealerScore()));
    }

    @FXML
    private void updatePlayerScore() {
        playerScore.setText(String.valueOf(game.getPlayerScore()));
    }

    private void updateUI(boolean revealDealer) {
        // Clear hands
        playerHandBox.getChildren().clear();
        dealerHandBox.getChildren().clear();

        // Update player's hand
        for (Card card : game.getPlayerHand()) {
            Image cardImage = new Image(getClass().getResource("/com/battlejack/cards/" + card.getFileName()).toExternalForm());
            ImageView cardView = new ImageView(cardImage);
            cardView.setFitWidth(100);
            cardView.setPreserveRatio(true);
            playerHandBox.getChildren().add(cardView);
        }

        // Update dealer's hand
        if (game.isGameOver() || revealDealer) {
            for (Card card : game.getDealerHand(true)) {
                Image cardImage = new Image(getClass().getResource("/com/battlejack/cards/" + card.getFileName()).toExternalForm());
                ImageView cardView = new ImageView(cardImage);
                cardView.setFitWidth(100);
                cardView.setPreserveRatio(true);
                dealerHandBox.getChildren().add(cardView);
            }
        } else {
            Card firstCard = game.getDealerHand(false).get(0);
            Image firstCardImage = new Image(getClass().getResource("/com/battlejack/cards/" + firstCard.getFileName()).toExternalForm());
            ImageView firstCardView = new ImageView(firstCardImage);
            firstCardView.setFitWidth(100);
            firstCardView.setPreserveRatio(true);

            Image backCardImage = new Image(getClass().getResource("/com/battlejack/cards/back.png").toExternalForm());
            ImageView backCardView = new ImageView(backCardImage);
            backCardView.setFitWidth(100);
            backCardView.setPreserveRatio(true);

            dealerHandBox.getChildren().addAll(firstCardView, backCardView);
        }
        updateDealerScore();
        updatePlayerScore();
        if (game.isGameOver()) {
            gameStatus.setText(game.getWinnerMessage());
            moneyText.setText(game.getMoneyText());
            winnings.setText(String.valueOf(game.getWinnings()));
            preGame();
        }
        if (game.isGameOver() && game.playerLost()) {
            endGame();
            SceneManager.switchScene("MainMenu.fxml");
        }
    }

    private void startGame() {
        betButton.setDisable(false);
        hitButton.setDisable(true);
        standButton.setDisable(true);
        dealButton.setDisable(false);
    }

    private void dealGame() {
        betButton.setDisable(true);
        hitButton.setDisable(false);
        standButton.setDisable(false);
        dealButton.setDisable(true);
    }

    private void canDoubleButton(){
        betButton.setDisable(true);
        hitButton.setDisable(false);
        standButton.setDisable(false);
        dealButton.setDisable(true);
        doubleDown.setDisable(false);
    }

    private void preGame() {
        betButton.setDisable(false);
        hitButton.setDisable(true);
        standButton.setDisable(true);
        dealButton.setDisable(true);
        doubleDown.setDisable(true);
    }

    private void endGame() {
        betButton.setDisable(true);
        hitButton.setDisable(true);
        standButton.setDisable(true);
        dealButton.setDisable(true);
        doubleDown.setDisable(true);
    }
}
