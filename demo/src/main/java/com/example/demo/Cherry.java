package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Random;

public class Cherry extends AnchorPane {

    private int Cherrycount = 0;

    private double Location = 0;


    @FXML
    private ImageView cherry;


    public void setTransparent(){
        cherry.setOpacity(0);
    }

    public Cherry(Block block){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cherry.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Random random = new Random();
        int result = random.nextInt(1,3);

        if (result == 1){
            double location = random.nextDouble(105, block.getX());
            System.out.println(location);
            cherry.setOpacity(100);
            cherry.setX(location);
            cherry.setLayoutY(525);
            Location = location;
        } else{
            cherry.setX(0);
            setTransparent();
        }

    }

    public void cherryReset(Block block){
        Random random = new Random();
        int result = random.nextInt(1,3);

        if (result == 1){
            double location = random.nextDouble(105, block.getX());
            System.out.println(location);
            cherry.setOpacity(100);
            cherry.setX(location);
            cherry.setLayoutY(525);
            Location = location;
        } else{
            cherry.setX(0);
            setTransparent();
        }
    }


    public double getLocation() {
        return Location;
    }

    public void disappear(){
    }

    public void RandomCherryGenerate(Block block){

    }


    public int getCherrycount() {
        return Cherrycount;
    }

    public void AddcherryCount(){
        Cherrycount++;
    }
}
