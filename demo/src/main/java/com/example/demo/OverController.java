package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.io.IOException;

public class OverController {

    @FXML
    Label Finalscore;

    @FXML
    Label Highscore;

    @FXML
    public void initialize() {
        Scores score = Scores.getInstance();
        System.out.println(score.getGameScore());
        Finalscore.setText(String.valueOf(score.getGameScore()));
        score.setHighScore();
        Highscore.setText(String.valueOf(score.getHighScore()));
        score.setGameScore(0);

    }


    public void onHomeButtonclick(ActionEvent event ) throws IOException {
        Parent GameView = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene Home = new Scene(GameView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }

    public void onReplayButtonClick(ActionEvent event ) throws IOException {
        Parent GameView = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene Home = new Scene(GameView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }

    public void onReviveUsingCherriesButtonClick(){}
}
