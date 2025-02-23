package com.battlejack;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class MusicManager {
    private static MediaPlayer mediaPlayer;
    private static final String MAIN_MENU = "src/main/resources/com/battlejack/music/mainmenu.wav";
    private static final String LEVEL_1 = "src/main/resources/com/battlejack/music/level1.wav";
    private static final String LEVEL_2= "src/main/resources/com/battlejack/music/level2.wav";
    private static final String LEVEL_3 = "src/main/resources/com/battlejack/music/level3.wav";

    public static void playMusic(String filePath) {
        stopMusic();
        Media media = new Media(new File(filePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.25);
        mediaPlayer.play();
    }

    public static void playLevelMusic(int level) {
        switch (level) {
            case 1:
                playMusic(LEVEL_1);
                break;
            case 2:
                playMusic(LEVEL_2);
                break;
            case 3:
                playMusic(LEVEL_3);
                break;
            default:
                playMusic(MAIN_MENU);
                break;
        }
    }

    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void lowerVolume(){
        mediaPlayer.setVolume(0.25);
    }
}
