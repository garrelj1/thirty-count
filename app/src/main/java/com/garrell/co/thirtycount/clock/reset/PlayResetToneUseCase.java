package com.garrell.co.thirtycount.clock.reset;

import android.media.MediaPlayer;

public class PlayResetToneUseCase {

    private final MediaPlayer mediaPlayer;

    public PlayResetToneUseCase(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void playTone() {
        mediaPlayer.start();
    }

}
