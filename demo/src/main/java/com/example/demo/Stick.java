package com.example.demo;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class Stick extends AnchorPane {
    private final double growSpeed = 2.5;


    private double length = 0;
    private rotateStick rotateStick = new rotateStick();
    @FXML
    public Rectangle stick;
    private growStick growStick = new growStick();


//    private Sound sticksound = GameSounds.getInstance().getSound("stick");

    public Stick() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("stick.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setLayoutX(99);
        this.setLayoutY(519);
    }


    boolean cangro = true;

    public void startGrow() {
        if (cangro) {
            System.out.println("stared");
            growStick.start();
        }
    }

    public void stopGrow() throws InterruptedException {
        System.out.println("stopped");
        if (cangro) {
            growStick.stop();
            rotateStick.start();
        }
        cangro = false;
//        wait(500);
    }

    private class growStick extends Thread {
        @Override
        public void run() {
            while (true) {
                stick.setHeight(length);
                length += 1;
                stick.setY(stick.getY() - 1);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    private class rotateStick extends Thread {
        @Override
        public void run() {
            Rotate rotater = new Rotate();
            rotater.setPivotX(0);
            rotater.setPivotY(0);
            stick.getTransforms().add(rotater);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotater.angleProperty(), 0)),
                    new KeyFrame(Duration.millis(270), new KeyValue(rotater.angleProperty(), 90)));
            timeline.play();
        }
    }

    public double getLength() {
        return this.length;
    }

    public void reset(double rand) {
        System.out.println("reset");
        growStick = new growStick();
        rotateStick = new rotateStick();
        stick.setY(stick.getY() + length);
        cangro = true;
        length = 0;
        this.setLayoutX(rand + this.getLayoutX());
        Rotate rotater = new Rotate();
        rotater.setPivotX(0);
        rotater.setPivotY(0);
        rotater.setAngle(-90);
        stick.getTransforms().add(rotater);
        stick.setHeight(0);
    }
}
