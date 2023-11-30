package com.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

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

    private void generateNextBlock(Block block) {
        Block blk = new Block();
        blk.getBlock().setOpacity(0);
        block = blk;
    }


    @FXML
    public void initialize() throws InterruptedException {



        Block block1 = new Block();


        block1.customiseWidth(80, 0);
        block1.removeDaPerfect();
        Block block2 = new Block();


        character = new Character();
        stick = new Stick();
        gameRoot.getChildren().addAll(stick, character,block1, block2);

        System.out.println(block2.getX()+ "\n");
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




                Duration delayDuration = Duration.millis(800); // Adjust delay duration as needed
                KeyFrame delay = new KeyFrame(delayDuration, event -> {
                    // Rest of the code after the delay
                    if ((stick.getLayoutX() + stick.getLength()) >= block2.getX()) {
                        character.move(block2.getX());

                        TranslateTransition transition1 = new TranslateTransition();
                        transition1.setNode(block2);
                        transition1.setByX(-block2.getX());





                        TranslateTransition transition2 = new TranslateTransition();
                        transition2.setNode(block1);
                        transition2.setByX(-100);

                        TranslateTransition transition3 = new TranslateTransition();
                        transition3.setNode(character);
                        transition3.setByX(-block2.getX());

                        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(200), stick);
                        fadeTransition2.setToValue(0);

                        fadeTransition2.setCycleCount(1);

                        ParallelTransition parallelTransition = new ParallelTransition();
                        parallelTransition.getChildren().addAll(transition1, transition2, transition3, fadeTransition2);

                        // Play the parallel transition


//            Timeline timeline2 = new Timeline(
//                    new KeyFrame(Duration.seconds(2))
//            );
//            timeline2.play();
                        FadeTransition fadeTransition;
                        Timeline timeline1 = new Timeline(
                                new KeyFrame(Duration.seconds(1), event1 ->block1.resetBlock())
                        );

                        fadeTransition = new FadeTransition(Duration.seconds(1), block1);



                        Timeline stickReset = new Timeline(
                                new KeyFrame(Duration.seconds(1), event1 ->stick.reset())
                        );







                        // Set the fromValue and toValue (opacity levels)




                        fadeTransition.setToValue(1);

                        fadeTransition.setCycleCount(1);

                        SequentialTransition transition = new SequentialTransition();
                        transition.getChildren().addAll(parallelTransition, timeline1, fadeTransition, stickReset);

                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.seconds(2), event2 ->transition.play())
                        );
                        timeline.play();
                    } else {
                        Parent GameView = null;
                        try {
                            GameView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scene Home = new Scene(GameView);
                        Stage window = (Stage) gameRoot.getScene().getWindow();
                        window.setScene(Home);
                        window.show();
                    }
                });

                // Create a timeline with the delay and play it
                Timeline timeline = new Timeline(delay);
                timeline.play();




            }
        });
//        try {
//            // Sleep for 5 seconds (5000 milliseconds)
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            // Handle interrupted exception (if necessary)
//            e.printStackTrace();
//        }


    }
}