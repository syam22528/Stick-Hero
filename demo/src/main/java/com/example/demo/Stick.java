package com.example.demo;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.IOException;

public class Stick extends AnchorPane {
    private final double growSpeed = 2.5;
    private double length = 0;
    private rotateStick rotateStick = new rotateStick();
    @FXML
    public Rectangle stick;
    private growStick growStick = new growStick();
    public Stick() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("stick.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startGrow(){
        System.out.println("stared");
        growStick.start();
    }
    public void stopGrow(){
        System.out.println("stopped");
        growStick.stop();
        rotateStick.start();
    }

    private class growStick extends Thread{
        @Override
        public void run() {
            while(true) {
                stick.setHeight(length);
                length += 1;
                stick.setY(stick.getY()-1);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private class rotateStick extends Thread{
        @Override
        public void run() {
            Rotate rotater = new Rotate();
            rotater.setPivotX(0);
            rotater.setPivotY(0);
            stick.getTransforms().add(rotater);
            for(int i = 0;i<89;i++) {
                    rotater.setAngle(1);
                    stick.getTransforms().add(rotater);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public double getLength(){
        return this.length;
    }
}
