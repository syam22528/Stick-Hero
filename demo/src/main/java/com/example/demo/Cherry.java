package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Cherry extends AnchorPane {

    @FXML
    ImageView cherry;

    private int Cherrycount = 0;

    public Cherry(){
        cherry.setImage(new Image("cherries.png"));
    }

    public void disappear(){
        cherry.setImage(null);
    }

    public void RandomCherryGenerate(){}

    public double getLocation(){
        return 0; // for now, will be changed after logic implementation
    }

    public int getCherrycount() {
        return Cherrycount;
    }

    public void AddcherryCount(){
        Cherrycount++;
    }
}
