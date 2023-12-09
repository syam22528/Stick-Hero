package com.example.demo;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Character extends AnchorPane {

    private double movementSpeed;
    private boolean isUpright = true;

    public boolean isUpright() {
        return isUpright;
    }

    public void setUpright(boolean upright) {
        isUpright = upright;
    }

    private boolean isDead = false;

    private double currPosition = 99;

    private int imagenum = 1;


    @FXML
    ImageView character;

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
        if (isUpright){
            character.setRotationAxis(Rotate.X_AXIS);
            character.setRotate(180);
            character.setY(74);

        }
        if (!isUpright){
            character.setRotate(0);
            character.setY(0);
        }
        isUpright = !isUpright;
    }

    public double getCharacterPosition(){
//        System.out.println("width : " + character.getImage().getWidth());
        return this.currPosition;
    }

    public void move(Double distance) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        Sound heroSound = GameSounds.getInstance().getSound("hero");
//        heroSound.playSound();
//        this.currPosition+= distance;
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(character);
        transition.setByX(distance);
        transition.setDuration(Duration.millis(1500));
        Timeline timeline = new Timeline((new KeyFrame(Duration.seconds(0.1), event ->{
            if (imagenum == 1 ){
                HeroSound heroSound = new HeroSound();
                Thread hero = new Thread(heroSound);
                hero.start();
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
//        heroSound.stopSound();

        character.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                character.setImage(new Image("inverseStickHero.png"));
                isUpright = false;
            }
        });



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


    public void setImage(Image image) {
        character.setImage(image);
    }
}
