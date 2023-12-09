package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class OverController {

    @FXML
    Label Finalscore;

    @FXML
    Label Highscore;

    Scores score;
    @FXML
    public void initialize() {
        score = Scores.getInstance();
        System.out.println("Ee"+score.getGameScore());
        Finalscore.setText(String.valueOf(score.getGameScore()));
        score.setHighScore();
        Highscore.setText(String.valueOf(score.getHighScore()));
    }


    public void onHomeButtonclick(ActionEvent event ) throws IOException {
        Parent GameView = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene Home = new Scene(GameView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }

    public void onReplayButtonClick(ActionEvent event ) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameController gg = new GameController(0);
        FXMLLoader replay = new FXMLLoader();
        replay.setController(gg);
        Scene Home = new Scene(replay.load(getClass().getResource("game.fxml")));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }

    public void onReviveUsingCherriesButtonClick(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameController gg = new GameController(score.getGameScore());
        FXMLLoader replay = new FXMLLoader();
        replay.setController(gg);
        Scene Home = new Scene(replay.load(getClass().getResource("game.fxml")));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Home);
        window.show();
    }
}
