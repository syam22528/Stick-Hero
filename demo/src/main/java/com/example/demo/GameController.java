package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    public void initialize() {
        character = new Character();
        stick = new Stick();
        gameRoot.getChildren().addAll(stick, character);
        stick.setLayoutX(42);
        stick.setLayoutY(412);
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
                stick.stopGrow();
                character.move(stick.getLength());
            }
        });
    }
}
