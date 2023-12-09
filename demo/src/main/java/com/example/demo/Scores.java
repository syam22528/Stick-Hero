package com.example.demo;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;

public class Scores extends AnchorPane implements Serializable {

    private static Scores score = null;

    public void serialize() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SavedGame.txt"))) {
            out.writeObject(this); // Write the Scores object
            System.out.println("Game data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception
            System.out.println("Error saving game data: " + e.getMessage());
        }
    }

    public static Scores deserialize() {
        Scores scores = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("SavedGame.txt"))) {
            scores = (Scores) in.readObject(); // Read the Scores object
            System.out.println("Game data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Log the exception
            System.out.println("Error loading game data: " + e.getMessage());
        }
        return scores;
    }

//    public static Scores getInstance(){
//        if (score == null){
//            score = new Scores();
//            Scores load = Scores.deserialize();
//            if(load != null){
//                score = load;
//            }
//
//        }
//        return score;
//    }
public static Scores getInstance() {
    if (score == null) {
        Scores load = Scores.deserialize();
        score = (load != null) ? load : new Scores();
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
