package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    //This button is for showing the GameOver scene, We are going to remove it as we implement the code for the character dying
    public void onGameOverclick(ActionEvent event ) throws IOException {
        Parent GameView = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
        Scene Home = new Scene(GameView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }


}
