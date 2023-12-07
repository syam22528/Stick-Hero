package com.example.demo;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class Scores extends AnchorPane {

    private ArrayList<Integer> scores;

    private int GameScore = 0;

    public int getHighScore(){
        return 0; //change later

    }

    @FXML
    Label scorecard;

    public Scores() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("score.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getGameScore() {
        return GameScore;
    }

    public void AddGameScore() {
        GameScore++;
        scorecard.setText(String.valueOf(GameScore));
    }

    public void AddGameScoretoArray(int score) {
        scores.add(score);
    }

    public void ResetScore(){
        GameScore = 0;
    }
}
