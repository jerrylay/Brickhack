package com.battlejack;

import javafx.fxml.FXML;

public class MainMenuController {
    
    @FXML
    private void startGame() {
        SceneManager.switchScene("BlackjackGame.fxml");
        MusicManager.playLevelMusic(2);
        MusicManager.lowerVolume();
    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }

    @FXML
    private void shop(){
        SceneManager.switchScene("Shop.fxml");
        MusicManager.playLevelMusic(1);
    }
}
