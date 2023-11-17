package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {




    @FXML
    protected void onPlaybuttonclick(Stage window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StickHero.class.getResource("game.fxml"));
        Scene Game = new Scene(fxmlLoader.load());
        window.setScene(Game);

    }


}
