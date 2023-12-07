package com.example.demo;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Random;

public class Block extends AnchorPane {

    public void becomeWhatYouWereMeantToBe(double rand){
        block.setX(rand+block.getX());
    }
    private double start_point;
    private double end_point;

    public double getStart_point() {
        return start_point;
    }

    public void setStart_point(double start_point) {
        this.start_point = start_point;
    }

    public double getEnd_point() {
        return end_point;
    }

    public void setEnd_point(double end_point) {
        this.end_point = end_point;
    }

    @FXML
    public Rectangle block;

    public Block() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("block.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Random random = new Random();
        double width = random.nextDouble(50,125);
        double x = random.nextDouble(150,300);
        this.setLayoutY(520);
        customiseWidth(width,x);
        addPerfect();
        this.start_point = block.getX();
        this.end_point = this.start_point + this.getBlockWidth();
        rand = x;
    }

    public void customiseWidth(double width, double x) {
        block.setWidth(width);
        block.setX(x);
        //TODO: find width of home platform and put it in fxml ka offset
    }

    @FXML
    Rectangle perfect = new Rectangle();
    public void addPerfect() {
        perfect.setX((block.getX() + (block.getWidth()/2)));
        perfect.setFill(Color.RED);
    }





    public void removeDaPerfect(){
        perfect.setHeight(0);
    }

    public double getX(){
        return block.getX();
    }

    double rand;
    public void resetBlock(double prev){
        Random random = new Random();
        int width = random.nextInt(50,200);
        double x = random.nextDouble(150,300);
        customiseWidth(width,prev+x);
        addPerfect();
        rand =x;
    }

    public Rectangle getBlock() {
        return block;
    }

    public double getLocation(){
        return this.getLayoutX();
    }
    public double getBlockWidth(){
        return this.getWidth();
    }

    public void HitRedArea(){
    }
}
