package com.example.demo;

// This implements Factory Design Pattern


import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GameSounds {

    private static GameSounds gameSounds = null;
    private static StickSound sticksound;

    private static HeroSound heroSound;

    private static BGSound bgSound;

    public static GameSounds getInstance() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (gameSounds == null) {
            bgSound = new BGSound();
            sticksound = new StickSound();
            heroSound = new HeroSound();
            gameSounds = new GameSounds();
        }
        return gameSounds;
    }


    public Sound getSound(String name) {
        if (Objects.equals(name, "stick")) {
            return sticksound;
        } else if (Objects.equals(name, "bg")) {
            return bgSound;
        } else if (Objects.equals(name, "hero")) {
            return heroSound;
        }
        return null;
    }
}


interface Sound {

    public void playSound();

    public void stopSound();

}

class StickSound extends AnchorPane implements Sound,Runnable {
    MediaPlayer mediaPlayer;


    public StickSound() {
//        String file = "src/main/resources/sticksound.wav";
//        System.out.println("Playing moojik");
//        Media media = new Media(new File(file).toURI().toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayer.play();
    }

    @Override
    public void playSound() {
        mediaPlayer.play();
    }

    @Override
    public void stopSound() {
        mediaPlayer.stop();
    }

    @Override
    public void run() {
        String file = "src/main/resources/sticksound.wav";
        System.out.println("Playing moojik");
        Media media = new Media(new File(file).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}

class BGSound extends AnchorPane implements Sound, Runnable {

    MediaPlayer mediaPlayer;

    private BGSound bg = null;


    @Override
    public void run() {
        if (bg == null) {
            String file = "src/main/resources/bgmusic.mp3";
            System.out.println("Playing moojik");
            Media media = new Media(new File(file).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }

    }

//    public BGSound() {
//        String file = "src/main/resources/Audio/bgmusic.mp3";
//        Media media = new Media(file);
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//    }

    @Override
    public void playSound() {
        mediaPlayer.play();
    }

    @Override
    public void stopSound() {
        mediaPlayer.stop();
    }


}

class HeroSound extends AnchorPane implements Sound, Runnable {

    MediaPlayer mediaPlayer;


//    public HeroSound() {
//        String file = "src/main/resources/Audio/herorun.mp3";
//        Media media = new Media(new File(file).toURI().toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//    }

    @Override
    public void playSound() {
        mediaPlayer.play();
    }

    @Override
    public void stopSound() {
        mediaPlayer.stop();
    }


    @Override
    public void run() {
        String file = "src/main/resources/herorun.mp3";
        System.out.println("Playing moojik");
        Media media = new Media(new File(file).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.play();


    }
}
