package com.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class GameController {


    @FXML
    AnchorPane gameRoot;

    Thread s;
    boolean isDead = false;
    Character character;

    Scores GameScore = Scores.getInstance();

    @FXML
    Label gamescore;
    Stick stick = new Stick();
    BGSound bg = new BGSound();
    Thread bgSound = new Thread(bg);
    boolean closeWorking = false;
    boolean canflip = false;
    actualListener actualListener = new actualListener();
    Block block2;
    private Block block1;
    Cherry cherry;

    public GameController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

    }

    private boolean isSafe() {
        return true; //just for now, will be changed after logic implementation
    }

    private void getDistanceToNextBlock() {

    }

    private void revive() {

    }

    private void endGame() {

    }

    public void onSaveButtonclick(ActionEvent event) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SavedGame"));
        out.writeObject(gamescore + "/n");
        out.writeObject(block2);
        out.close();


        Parent GameView = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene Home = new Scene(GameView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }

    @FXML
    public void initialize() throws InterruptedException {
        if (!gameRoot.getChildren().contains(bg)) {
            gameRoot.getChildren().add(bg);
//            bg.playSound();
        }
        block1 = new Block();
        block1.customiseWidth(100, 0);
        block1.removeDaPerfect();
        block2 = new Block();
        stick.grower.addListener(actualListener);
        character = new Character();
        cherry = new Cherry(block1, block2);
        gameRoot.getChildren().addAll(character, block1, block2, stick, cherry);
        System.out.println(block2.getX() + "\n");


        bgSound.start();
        gameRoot.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StickSound sound = new StickSound();
                s = new Thread(sound);
                s.start();
                stick.startGrow();
            }
        });
        gameRoot.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)

            {
                if (event.isControlDown() && event.getCode() == KeyCode.S) {
                    Text perfect = new Text("Saving...");
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

                    try {
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SavedGame.txt"));
                        out.writeObject(GameScore); // Write game score directly
                        System.out.println("Game data saved successfully.");
                    } catch (IOException e) {
                        e.printStackTrace(); // Log the exception
                        System.out.println("Error saving game data: " + e.getMessage());
                    }
                }
                Parent GameView = null;
                try {
                    GameView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Homepage.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene Home = new Scene(GameView);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(Home);
                window.show();
            }
        });
        gameRoot.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (canflip)
                    character.reverseCharacter();
                else
                    return;
                if (character.getCharacterPosition() >= block2.getBoundsInParent().getMaxX()-block2.blockWidth/2) {
                    TranslateTransition fallingTransition = new TranslateTransition();
                    fallingTransition.setNode(character);
                    fallingTransition.setByY(300);
                    Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> fallingTransition.play()));
                    Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(1), event1 -> {
                        Parent GameView = null;
                        try {
                            GameView = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                            Scene GameOver = new Scene(GameView);
                            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                            window.setScene(GameOver);
                            window.show();
                        } catch (IOException e) {
                            try {
                                GameView = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            Scene GameOver = new Scene(GameView);
                            Stage window = new Stage();
                            window.setScene(GameOver);
                            window.show();
                            throw new RuntimeException(e);
                        }


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
                    s.stop();
                    stick.stopGrow();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                closeWorking = true;
                Duration delayDuration = Duration.millis(800);
                KeyFrame delay = new KeyFrame(delayDuration, event -> {
                    character.setCurrPosition(stick.getLayoutX());
                    System.out.println("character x: " + character.getCharacterPosition() + " stick x : " + stick.getLayoutX() + " length : " + stick.getLength() + " block endpoint : " + block2.getEnd_point() + " block start point : " + block2.getStart_point() + "\n");
                    if ((stick.getLayoutX() + stick.getLength()) <= block2.getEnd_point() && (stick.getLayoutX() + stick.getLength()) >= block2.getStart_point()) {

                        GameScore.AddGameScore();
                        gamescore.setText(String.valueOf(GameScore.getGameScore()));
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        canflip = true;
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
                            GameScore.AddGameScore();
                            gamescore.setText(String.valueOf(GameScore.getGameScore()));

                        }

                        Double distance = block2.rand - 93 + block2.blockWidth;
                        try {
                            character.move(distance);
                        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                            throw new RuntimeException(e);
                        }

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
                        TranslateTransition transition4 = new TranslateTransition();
                        transition4.setNode(cherry);
                        transition4.setByX(-block2.rand);

                        double rand = block2.rand;
                        ParallelTransition parallelTransition = new ParallelTransition();
                        parallelTransition.getChildren().addAll(transition1, transition2, transition3, fadeTransition2,transition4);
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
                                new KeyFrame(Duration.millis(1), event1 -> {
                                    closeWorking = false;
                                    canflip = false;
                                })
                        );
                        Timeline timelineCherry = new Timeline(
                                new KeyFrame(Duration.millis(400), event1 -> cherry.cherryReset(block1, block2))
                        );
                        SequentialTransition transition = new SequentialTransition();
                        transition.getChildren().addAll(parallelTransition, timeline2, timeline3, timeline1, timeline4,timelineCherry);
                        Timeline timeline = new Timeline(

                                new KeyFrame(Duration.seconds(1), event2 -> {
                                    if (character.isUpright())
                                        transition.play();
                                    else {
                                        TranslateTransition fallingTransition = new TranslateTransition();
                                        fallingTransition.setNode(character);
                                        fallingTransition.setByY(300);
                                        Timeline timeline5 = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> fallingTransition.play()));
                                        Timeline timeline6 = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> {
//                                    FXMLLoader Gameview = null;
                                            Parent GameView = null;
                                            OverController overController = null;
                                            try {
                                                GameView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
                                                bgSound.stop();
                                                Stage window = (Stage) gameRoot.getScene().getWindow();
                                                Scene GameOver = new Scene(GameView);
                                                window.setScene(GameOver);
                                                window.show();
                                            } catch (Exception e) {
                                                Parent HomeView = null;
                                                try {
                                                    HomeView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                bgSound.stop();
                                                Stage window = new Stage();
                                                Scene home = new Scene(HomeView);
                                                window.setScene(home);
                                                window.show();

                                            }



                                        }));
                                        SequentialTransition Transition = new SequentialTransition();
                                        Transition.getChildren().addAll(timeline5, timeline6);
                                        Transition.play();
                                    }
                                })
                        );
                        timeline.play();
                    } else {
                        isDead = true;
                        Timeline timeline1 = new Timeline(
                                new KeyFrame(Duration.millis(1), event1 -> {
                                    try {
                                        character.move(stick.getLength());
                                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                        );
                        TranslateTransition fallingTransition = new TranslateTransition();
                        fallingTransition.setNode(character);
                        fallingTransition.setByY(300);
                        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1500), event1 -> fallingTransition.play()));
                        Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(1500), event1 -> {
                            Parent GameView = null;
                            try {
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

    public boolean checkIfOutside(double x, double y){ //x = stick, y = block end
        if(x > y){
            return true;
        }
        return false;
    }

    public boolean checkifBehind(double x, double y){ //x = stick, y = block start
        if(x < y){
            return true;
        }
        return false;
    }

    public boolean isSafe(double x, double start_point, double end_point){
        if(x >= start_point && x <= end_point){
            return true;
        }
        return false;
    }

    class actualListener implements Stick.growListener {
        @Override
        public void onePixelGrow() {
            stick.stick.setHeight(stick.length);
            stick.length += 1;
            stick.stick.setY(stick.stick.getY() - 1);
        }
    }
}