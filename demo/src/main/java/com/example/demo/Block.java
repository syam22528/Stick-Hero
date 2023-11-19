package com.example.demo;


import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class Block extends AnchorPane {

    @FXML
    public Rectangle block;

    public Block() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("block.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        customiseWidth();
        addPerfect();
    }

    public void customiseWidth() {
        Random random = new Random();
        block.setWidth(random.nextInt(50, 300));
        block.setX(random.nextDouble(100, 200));
//        block.setY(2);
        //TOdo: find width of home platform and put it in fxml ka offset
    }

    @FXML
    Rectangle perfect = new Rectangle();
    public void addPerfect() {
        perfect.setX((block.getX() + (block.getWidth()/2)));
        perfect.setFill(Color.RED);
    }


    public double getLocation(){
        return this.getLayoutX();
    }

    public double getBlockWidth(){
        return this.getWidth();
    }

    public void HitRedArea(){
    }
}
