package com.battlejack;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ShopController {

    @FXML private Text winningsText;

    private static final int BOOST_2X_COST = 5000;
    private static final int BOOST_3X_COST = 10000;

    public void initialize() {
        updateWinningsText();
    }

    @FXML
    private void boost2() {
        if (GameState.getTotalWinnings() >= BOOST_2X_COST) {
            GameState.addWinnings(-BOOST_2X_COST);
            GameState.addMultplier(1);
            updateWinningsText();
        } else {
            winningsText.setText("Not enough winnings!");
        }
    }

    @FXML
    private void boost3() {
        if (GameState.getTotalWinnings() >= BOOST_3X_COST) {
            GameState.addWinnings(-BOOST_3X_COST);
            GameState.addMultplier(2);
            updateWinningsText();
        } else {
            winningsText.setText("Not enough winnings!");
        }
    }

    @FXML
    private void exit() {
        SceneManager.switchScene("MainMenu.fxml");
        MusicManager.playLevelMusic(0);
    }

    private void updateWinningsText() {
        winningsText.setText("Winnings: $" + GameState.getTotalWinnings());
    }
}
