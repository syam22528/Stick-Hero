package com.example.demo;

import com.almasb.fxgl.entity.action.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController {



    public void onPlaybuttonclick(ActionEvent event ) throws IOException {
        Parent GameView = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene Game = new Scene(GameView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Game);
        window.show();

    }

    public void onHomeButtonclick(ActionEvent event ) throws IOException {
        Parent GameView = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene Home = new Scene(GameView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }

    //This button is for showing the GameOver scene, We are going to remove it as we implement the code for the character dying
    public void onGameOverclick(ActionEvent event ) throws IOException {
        Parent GameView = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
        Scene Home = new Scene(GameView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }
}