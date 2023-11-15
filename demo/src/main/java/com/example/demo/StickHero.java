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
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StickHero extends Application {

    Stage window;
    Scene Home, Game;


    @Override
    public void start(Stage stage) throws IOException {
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

        Homebutton1.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-x: 500; -fx-translate-y: 0;");
        Homebutton2.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-x: -500; -fx-translate-y: 300;");
        Homebutton3.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-x: 500; -fx-translate-y: 300;");
        Homebutton4.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; -fx-translate-x: -500; -fx-translate-y: 0;");

        PlayGameButton.setStyle("-fx-background-radius: 120; -fx-pref-width: 120;-fx-pref-height: 120; -fx-background-color: red; ");
        PlayGameButton.setOnAction(e-> window.setScene(Game));

        Image HomebackgroundImage = new Image("mountain.jpeg");
        BackgroundImage Homebmg = new BackgroundImage(HomebackgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bmgsize);
        Background Homebackground = new Background(Homebmg);

        Image stickHeroImage = new Image("stickhero.png");
        ImageView StickHeroImage = new ImageView(stickHeroImage);
        StickHeroImage.setFitHeight(200.0);
        StickHeroImage.setFitWidth(200.0);
        StickHeroImage.setStyle("-fx-translate-x: 0; -fx-translate-y: 150;");

        Image pillarImage = new Image("pillar.jpg");
        ImageView PillarImage = new ImageView(pillarImage);
        PillarImage.setFitHeight(250.0);
        PillarImage.setFitWidth(100.0);
        PillarImage.setStyle("-fx-translate-x: 5; -fx-translate-y: 325;");







        StackPane layout1 = new StackPane();
        Home = new Scene(layout1, 1300, 900);
        layout1.setBackground(Homebackground);
        layout1.getChildren().addAll(label1, PlayGameButton, Homebutton1, Homebutton2, Homebutton3, Homebutton4, StickHeroImage, PillarImage);
        StackPane.setAlignment(label1, Pos.TOP_CENTER);

        StackPane layout2 = new StackPane();
        Image GamebackgroundImage = new Image("clouds.jpg");
        BackgroundImage Gamebmg = new BackgroundImage(GamebackgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bmgsize);
        Background Gamebackground = new Background(Gamebmg);


        layout2.setBackground(Gamebackground);
        Game = new Scene(layout2, 1300, 900);



        window.setTitle("Stick hero");
        window.setScene(Home);
        window.show();

    }

    public static void main(String[] args) {
        launch();
    }
}