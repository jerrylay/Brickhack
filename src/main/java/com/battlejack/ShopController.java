package com.battlejack;

import javafx.fxml.FXML;

public class ShopController {
    @FXML
    private void exit() {
        SceneManager.switchScene("MainMenu.fxml");
        MusicManager.playLevelMusic(0);
        MusicManager.lowerVolume();
    }

    @FXML
    private void boost2() {
        GameState.addMultplier(2);
    }

    @FXML
    private void boost3() {
        GameState.addMultplier(3);
    }
}
