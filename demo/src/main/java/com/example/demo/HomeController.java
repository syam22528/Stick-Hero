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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController {
    public void onPlaybuttonclick(ActionEvent event ) throws IOException {
        Parent GameView = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene Game = new Scene(GameView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Game);
        window.show();
    }

    public void onSettingsButtonClick(){
    }

    public void onHelpButtonClick(){
    }
}