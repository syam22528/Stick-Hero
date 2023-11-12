package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StickHero extends Application {

    Stage window;
    Scene Home, Game;


    @Override
    public void start(Stage stage) throws IOException {
        window = stage;


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Label label1 = new Label("Welcome to the Game");

        Button PlayGame = new Button("Play");
        PlayGame.setOnAction(e-> window.setScene(Game));


        StackPane layout1 = new StackPane();
        Home = new Scene(layout1, 1300, 900);
        layout1.getChildren().addAll(label1, PlayGame);
        StackPane.setAlignment(label1, Pos.TOP_CENTER);

        StackPane layout2 = new StackPane();
        Game = new Scene(layout2, 1300, 900);
        Image backgroundImage = new Image("clouds.jpg");

        BackgroundSize bmgsize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage bmg = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bmgsize);
        Background background = new Background(bmg);
        layout2.setBackground(background);
        window.setTitle("Stick hero");
        window.setScene(Home);
        window.show();

    }

    public static void main(String[] args) {
        launch();
    }
}