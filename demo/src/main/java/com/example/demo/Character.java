package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Character {

    private double movementSpeed;
    private boolean isUpright;

    private boolean isDead;

    private double currPosition;

    private Image character = new Image("stickhero.png");
    private Image inverse_character = new Image("inverseStickHero.png");
    private Image stickHeroImage = new Image("stickhero.png");
    private ImageView StickHeroImage = new ImageView(stickHeroImage);


    public Character() {
    }

    public ImageView getStickHeroImage() {
        return StickHeroImage;
    }

    public void setStickHeroCoordinates(int x, int y){
        StickHeroImage.setStyle("-fx-translate-x:" + x +";"+ "-fx-translate-y:"+ y +";");
    }


    public void setStickHerosize(int height, int width){
        StickHeroImage.setFitWidth(width);
        StickHeroImage.setFitHeight(height);
    }

    public void reverseCharacter(){

    }


    public void move(){}

    public void die(){}
}
