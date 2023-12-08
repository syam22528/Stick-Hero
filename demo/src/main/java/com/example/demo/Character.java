package com.example.demo;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;

public class Character extends AnchorPane {

    private double movementSpeed;
    private boolean isUpright = true;

    private boolean isDead = false;

    private double currPosition;

    private int imagenum = 1;

    @FXML
    private ImageView character;

    public Character() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("character.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        character.setLayoutY(412);
    }

    public void reverseCharacter(){

    }


    public void move(Block block2){
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(character);
        transition.setByX(block2.rand-93+block2.blockWidth);
        transition.setDuration(Duration.millis(1000));




        Timeline timeline = new Timeline((new KeyFrame(Duration.seconds(0.1), event ->{
            if (imagenum == 1 ){
                character.setImage(new Image("stickherorunning.png"));
                imagenum = 2;

            } else {
                character.setImage(new Image("stickhero.png"));
                imagenum = 1;
            }
        })));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        transition.play();

        transition.setOnFinished(event -> timeline.stop());
        character.setImage(new Image("stickhero.png"));



    }
    public double getPosition(){
        return currPosition;
    }

    public void die(){
        isDead = true;
    }

    public void setCurrPosition(double position){
        currPosition = position;
    }


}
