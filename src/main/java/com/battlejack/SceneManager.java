package com.battlejack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    private static Stage stage;

    // Set the main stage
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    // Switch to a new scene
    public static void switchScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource("/com/battlejack/" + fxmlFile));
            stage.setScene(new Scene(root, 1200, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
