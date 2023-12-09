package com.example.demo;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GameSounds {

    private static GameSounds gameSounds = null;
    private static StickSound sticksound;

    private static HeroSound heroSound;

    private static BGSound bgSound;

    public static GameSounds getInstance() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (gameSounds == null){
            bgSound = new BGSound();
            sticksound = new StickSound();
            heroSound = new HeroSound();
            gameSounds = new GameSounds();
        }
        return gameSounds;
    }



    public Sound getSound(String name) {
        if (Objects.equals(name, "stick")){
            return sticksound;
        } else if (Objects.equals(name, "bg")) {
            return bgSound;
        } else if (Objects.equals(name, "hero")){
            return heroSound;
        }
        return null;
    }
}


interface Sound {

    public void playSound();

    public void stopSound();

}

class StickSound implements Sound{
    MediaPlayer mediaPlayer;



    public StickSound() {
        String file = "src/main/resources/Audio/sticksound.wav";
        Media media = new Media(new File(file).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @Override
    public void playSound() {
        mediaPlayer.play();
    }

    @Override
    public void stopSound() {
        mediaPlayer.stop();
    }
}

class BGSound implements Sound{

    MediaPlayer mediaPlayer;



    public BGSound() {
        String file = "src/main/resources/Audio/bgmusic.mp3";
        Media media = new Media(file);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @Override
    public void playSound() {
        mediaPlayer.play();
    }

    @Override
    public void stopSound() {
        mediaPlayer.stop();
    }



}

class HeroSound implements Sound{

    MediaPlayer mediaPlayer;



    public HeroSound() {
        String file = "src/main/resources/Audio/herorun.mp3";
        Media media = new Media(new File(file).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @Override
    public void playSound() {
        mediaPlayer.play();
    }

    @Override
    public void stopSound() {
        mediaPlayer.stop();
    }



}
