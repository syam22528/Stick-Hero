package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StickHero extends Application {

    Stage window;
    Scene Home;


    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(StickHero.class.getResource("HomePage.fxml"));
        Parent sc = fxmlLoader.load();
        Home = new Scene(sc);
        window.setTitle("codimg");
        window.setScene(Home);
        window.setResizable(false);
        window.show();
    }
    public static void main(String[] args) {
        launch();
    }
}