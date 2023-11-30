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


    public void move(double l){
        moveHero moveHero = new moveHero(l);
        moveHero.start();
    }

    public double getPosition(){
        return currPosition;
    }

    private class moveHero extends Thread{
        double l;

        public moveHero(double l) {
            this.l = l;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(int i = 0;i<l;i++){
                character.setX(character.getX()+1);

                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            setCurrPosition(l);
            System.out.println(getPosition()+ "\n");
        }

    }

    public void die(){
        isDead = true;
    }

    public void setCurrPosition(double position){
        currPosition = position;
    }
}
