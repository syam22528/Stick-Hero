package com.example.demo;


import javafx.scene.image.*;

public class Block {
    private Image blockImage = new Image("block.jpg");

    private ImageView BlockImage = new ImageView(blockImage);

    Block(){
    }

    public ImageView getBlockImage() {
        return BlockImage;
    }

    public void setBlockImageCoordinates(int x, int y){
        BlockImage.setStyle("-fx-translate-x:" + x +";"+ "-fx-translate-y:"+ y +";");
    }

    public void setBlockImagesize(int height, int width){
        BlockImage.setFitHeight(height);
        BlockImage.setFitWidth(width);
    }


}
