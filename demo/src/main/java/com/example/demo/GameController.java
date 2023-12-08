package com.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    @FXML
    AnchorPane gameRoot;

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

    Stick stick = new Stick();
    @FXML
    Scores scorern;

    boolean closeWorking = false;

    @FXML
    public void initialize() throws InterruptedException {
        Block block1 = new Block();
        block1.customiseWidth(100, 0);
        block1.removeDaPerfect();
        Block block2 = new Block();

        character = new Character();
        gameRoot.getChildren().addAll(character, block1, block2, stick);
        System.out.println(block2.getX() + "\n");
        gameRoot.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stick.startGrow();
            }
        });
        gameRoot.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (closeWorking)
                    character.reverseCharacter();
                if (character.getBoundsInLocal().getMaxX() > block2.getBoundsInLocal().getMaxX()) {
                    TranslateTransition fallingTransition = new TranslateTransition();
                    fallingTransition.setNode(character);
                    fallingTransition.setByY(300);
                    Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> fallingTransition.play()));
                    Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(1), event1 -> {
//                                    FXMLLoader Gameview = null;
                        Parent GameView = null;
                        OverController overController = null;
                        try {
//                                        Gameview = new FXMLLoader(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
                            //                            overController = (OverController) Gameview.getController();
                            //                            overController.setFinalscore(scorern);
//                                        GameView = Gameview.load();
                            GameView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scene GameOver = new Scene(GameView);

                        Stage window = (Stage) gameRoot.getScene().getWindow();
                        window.setScene(GameOver);
                        window.show();
                    }));
                    SequentialTransition Transition = new SequentialTransition();
                    Transition.getChildren().addAll(timeline2, timeline3);
                    Transition.play();
                }
            }
        });
        gameRoot.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (closeWorking)
                    return;
                try {
                    stick.stopGrow();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Duration delayDuration = Duration.millis(800);
                KeyFrame delay = new KeyFrame(delayDuration, event -> {

                    System.out.println("character x: " + character.getLayoutX() + " stick x : " + stick.getLayoutX() + " length : " + stick.getLength() + " block endpoint : " + block2.getEnd_point() + " block start point : " + block2.getStart_point() + "\n");
                    if ((stick.getLayoutX() + stick.getLength()) <= block2.getEnd_point() && (stick.getLayoutX() + stick.getLength()) >= block2.getStart_point()) {
                        scorern.AddGameScore();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        closeWorking = true;
                        if ((stick.getLayoutX() + stick.getLength()) <= (block2.perfect.getX() + 10) && (stick.getLayoutX() + stick.getLength()) >= block2.perfect.getX()) {
                            Text perfect = new Text("PERFECT! ");
                            perfect.setLayoutX(300);
                            perfect.setLayoutY(100);
                            perfect.setFill(Color.RED);
                            perfect.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                            gameRoot.getChildren().add(perfect);
                            FadeTransition textDisappear = new FadeTransition();
                            textDisappear.setDuration(Duration.millis(1000));
                            textDisappear.setNode(perfect);
                            textDisappear.setFromValue(10);
                            textDisappear.setToValue(0);
                            textDisappear.play();
                            scorern.AddGameScore();

                        }

                        Double distance = block2.rand - 93 + block2.blockWidth;
                        character.move(distance);

                        TranslateTransition transition1 = new TranslateTransition();
                        transition1.setNode(block2);
                        transition1.setByX(-block2.rand);
                        TranslateTransition transition2 = new TranslateTransition();
                        transition2.setNode(block1);
                        transition2.setByX(-block2.rand);
                        TranslateTransition transition3 = new TranslateTransition();
                        transition3.setNode(character);
                        transition3.setByX(-(block2.rand - 93 + block2.blockWidth));
                        TranslateTransition fadeTransition2 = new TranslateTransition();
                        fadeTransition2.setNode(stick);
                        fadeTransition2.setByX(-block2.rand);
                        double rand = block2.rand;
                        ParallelTransition parallelTransition = new ParallelTransition();
                        parallelTransition.getChildren().addAll(transition1, transition2, transition3, fadeTransition2);
                        Timeline timeline1 = new Timeline(
                                new KeyFrame(Duration.millis(1), event1 -> block2.resetBlock(block2.getX()))
                        );
                        Timeline timeline2 = new Timeline(
                                new KeyFrame(Duration.millis(1), event1 -> stick.reset(rand))
                        );
                        Timeline timeline3 = new Timeline(
                                new KeyFrame(Duration.millis(1), event1 -> block1.becomeWhatYouWereMeantToBe(rand))
                        );
                        Timeline timeline4 = new Timeline(
                                new KeyFrame(Duration.millis(1), event1 -> closeWorking = false)
                        );
                        SequentialTransition transition = new SequentialTransition();
                        transition.getChildren().addAll(parallelTransition, timeline2, timeline3, timeline1, timeline4);
                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.seconds(1), event2 -> {
                                    if(character.isUpright())
                                        transition.play();
                                    else{
                                        TranslateTransition fallingTransition = new TranslateTransition();
                                        fallingTransition.setNode(character);
                                        fallingTransition.setByY(300);
                                        Timeline timeline5 = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> fallingTransition.play()));
                                        Timeline timeline6 = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> {
//                                    FXMLLoader Gameview = null;
                                            Parent GameView = null;
                                            OverController overController = null;
                                            try {
//                                        Gameview = new FXMLLoader(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
                                                //                            overController = (OverController) Gameview.getController();
                                                //                            overController.setFinalscore(scorern);
//                                        GameView = Gameview.load();
                                                GameView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            Scene GameOver = new Scene(GameView);

                                            Stage window = (Stage) gameRoot.getScene().getWindow();
                                            window.setScene(GameOver);
                                            window.show();
                                        }));
                                        SequentialTransition Transition = new SequentialTransition();
                                        Transition.getChildren().addAll(timeline5, timeline6);
                                        Transition.play();
                                    }
                                })
                        );
                        timeline.play();
                    } else {
                        Timeline timeline1 = new Timeline(
                                new KeyFrame(Duration.millis(1), event1 -> character.move(stick.getLength()))
                        );
                        TranslateTransition fallingTransition = new TranslateTransition();
                        fallingTransition.setNode(character);
                        fallingTransition.setByY(300);
                        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> fallingTransition.play()));
                        Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> {
//                                    FXMLLoader Gameview = null;
                            Parent GameView = null;
                            OverController overController = null;
                            try {
//                                        Gameview = new FXMLLoader(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
                                //                            overController = (OverController) Gameview.getController();
                                //                            overController.setFinalscore(scorern);
//                                        GameView = Gameview.load();
                                GameView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Scene GameOver = new Scene(GameView);

                            Stage window = (Stage) gameRoot.getScene().getWindow();
                            window.setScene(GameOver);
                            window.show();
                        }

                        ));

                        SequentialTransition Transition = new SequentialTransition();
                        Transition.getChildren().addAll(timeline1, timeline2, timeline3);
                        Transition.play();
                    }
                });


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