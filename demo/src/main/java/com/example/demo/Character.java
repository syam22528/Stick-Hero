package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class Character extends AnchorPane {

    private double movementSpeed;
    private boolean isUpright = true;

    private boolean isDead = false;

    private double currPosition;

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
