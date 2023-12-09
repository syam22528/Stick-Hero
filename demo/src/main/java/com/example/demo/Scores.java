package com.example.demo;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class Scores extends AnchorPane {

    private static Scores score = null;

    public static Scores getInstance(){
        if (score == null){
            score = new Scores();

        }
        return score;
    }

    private ArrayList<Integer> scores;

    private int highScore = 0;

    private int GameScore = 0;

    public int getHighScore(){
        return highScore;
    }


    public void DisplayScore(){
        System.out.println(GameScore);
    }


    public int getGameScore() {
        return GameScore;
    }

    public void AddGameScore() {
        GameScore++;
    }

    public void setGameScore(int gameScore) {
        GameScore = gameScore;
    }

    public void AddGameScoretoArray(int score) {

        scores.add(score);
    }

    public void setHighScore() {
        if (highScore < GameScore){
            highScore = GameScore;
        }
    }

    public void ResetScore(){
        GameScore = 0;
    }
}
