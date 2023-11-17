package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StickHero extends Application {

    Stage window;
    Scene Home, Game;


    @Override
    public void start(Stage stage) throws IOException {
        Character stickhero = new Character();
        Block block = new Block();
        window = stage;


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Label label1 = new Label("STICK\nHERO");

        label1.setFont(Font.font("Arial", FontWeight.BOLD, 100));
//        label1.setTextFill(Color.RED);

        BackgroundSize bmgsize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);




        Button PlayGameButton = new Button("Play");
        Button Homebutton1 = new Button("button");
        Button Homebutton2 = new Button("button");
        Button Homebutton3 = new Button("button");
        Button Homebutton4 = new Button("button");

        PlayGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        PlayGameButton.setTextFill(Color.WHITE);
        Homebutton1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Homebutton1.setTextFill(Color.WHITE);
        Homebutton2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Homebutton2.setTextFill(Color.WHITE);
        Homebutton3.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Homebutton3.setTextFill(Color.WHITE);
        Homebutton4.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Homebutton4.setTextFill(Color.WHITE);



        Homebutton1.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-x: 700; -fx-translate-y: 0;");
        Homebutton2.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-x: -700; -fx-translate-y: 300;");
        Homebutton3.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-x: 700; -fx-translate-y: 300;");
        Homebutton4.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-x: -700; -fx-translate-y: 0;");

        PlayGameButton.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-y: -150;");
        PlayGameButton.setOnAction(e-> window.setScene(Game));

        Image HomebackgroundImage = new Image("mountain.jpeg");
        BackgroundImage Homebmg = new BackgroundImage(HomebackgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bmgsize);
        Background Homebackground = new Background(Homebmg);


        stickhero.setStickHerosize(400, 400);
        stickhero.setStickHeroCoordinates(0, 51);
        block.setBlockImagesize(400, 300);
        block.setBlockImageCoordinates(0, 350);



        StackPane layout2 = new StackPane();
        Image GamebackgroundImage = new Image("clouds.jpg");
        BackgroundImage Gamebmg = new BackgroundImage(GamebackgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bmgsize);
        Background Gamebackground = new Background(Gamebmg);
        layout2.setBackground(Gamebackground);
        Game = new Scene(layout2, 1920, 1080);

        StackPane layout1 = new StackPane();
        Home = new Scene(layout1, 1920, 1080);
        layout1.setBackground(Homebackground);
        layout1.getChildren().addAll(label1, PlayGameButton, Homebutton1, Homebutton2, Homebutton3, Homebutton4, stickhero.getStickHeroImage(), block.getBlockImage());
        StackPane.setAlignment(label1, Pos.TOP_CENTER);



        window.setMaximized(true);
        window.setTitle("Stick hero");
        window.setScene(Home);
        window.show();

    }

    public static void main(String[] args) {
        launch();
    }
}