package com.battlejack;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private static final AudioClip cardSlide = new AudioClip(SoundManager.class.getResource("/com/battlejack/music/cardplace.wav").toExternalForm());

    public static void playSlide(){
        cardSlide.play();
    }
}
