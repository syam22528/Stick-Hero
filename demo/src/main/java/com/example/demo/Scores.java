package com.example.demo;


import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Scores extends AnchorPane {
    int currScore;

    private ArrayList<Integer> scores;

    private int GameScore = 0;

    public int getHighScore(){
        return 0; //change later

    }

    public int getGameScore() {
        return GameScore;
    }

    public void AddGameScore() {
        GameScore++;
    }

    public void AddGameScoretoArray(int score) {
        scores.add(score);
    }

    public void ResetScore(){
        GameScore = 0;
    }



}
