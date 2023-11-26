package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class GameController {

    @FXML
    AnchorPane gameRoot;

    Stick stick;
    Character character;

    private boolean isSafe() {
        return true; //just for now, will be changed after logic implementation
    }

    private void getDistanceToNextBlock() {

    }

    private void revive() {

    }

    private void endGame() {

    }

    private void generateNextBlock() {

    }

    @FXML
    public void initialize() throws InterruptedException {



        Block startBlock = new Block();


        startBlock.customiseWidth(80, 0);
        startBlock.removeDaPerfect();
        Block block2 = new Block();
        Line path = new Line(block2.getLayoutX()+150, 107, -80, 107);


        PathTransition transition = new PathTransition();

        character = new Character();
        stick = new Stick();
        gameRoot.getChildren().addAll(stick, character,startBlock, block2);
        stick.setLayoutX(80);
        stick.setLayoutY(519);
        character.setLayoutY(412);
        gameRoot.addEventFilter(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stick.startGrow();
            }
        });
        gameRoot.addEventFilter(MouseEvent.MOUSE_RELEASED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    stick.stopGrow();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                character.move(stick.getLength());
            }
        });
//        try {
//            // Sleep for 5 seconds (5000 milliseconds)
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            // Handle interrupted exception (if necessary)
//            e.printStackTrace();
//        }
        transition.setNode(block2);
        transition.setPath(path);
        transition.setDuration(Duration.seconds(3));
        transition.setCycleCount(1);




        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> transition.play())

        );


        timeline.play();





    }
}