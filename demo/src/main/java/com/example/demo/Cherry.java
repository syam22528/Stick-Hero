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

    public Cherry(Block block1, Block block2){
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

        if (result == 2){
            double location = random.nextDouble(block1.getX()+100, block2.getX()-40);
            System.out.println(location);
            cherry.setOpacity(100);
            cherry.setX(location);
            cherry.setLayoutY(525);
            Location = location;
        } else{
            setTransparent();
        }

    }

    public void cherryReset(Block block1, Block block2){
        Random random = new Random();
        int result = random.nextInt(1,3);
        System.out.println("block1.getx:"+ block1.getX() + "\n");
        System.out.println("block2.getx:"+ block2.getX()+ "\n");

        if (result == 2){

            double location = random.nextDouble(block1.getX()+100, block2.getX()-40);

            System.out.println(location);
            cherry.setOpacity(100);
            cherry.setX(location);
            cherry.setLayoutY(525);
            Location = location;
        } else{
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
