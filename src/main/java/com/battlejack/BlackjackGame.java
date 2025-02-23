package com.battlejack;

import javafx.application.Application;
import javafx.stage.Stage;

public class BlackjackGame extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager.setStage(primaryStage);
        SceneManager.switchScene("MainMenu.fxml");
        MusicManager.playLevelMusic(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
