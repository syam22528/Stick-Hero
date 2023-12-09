//package com.example.demo;
//
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestClass {
//    Stage window;
//    Scene Home;
//    public class TestClass {
//
//        @Test
//        public void RunTest1() {
//            // Initialize JavaFX Toolkit (required before using JavaFX components in JUnit)
//            new JFXPanel();
//
//            // Run later on the JavaFX application thread
//            Platform.runLater(() -> {
//                try {
//                    FXMLLoader fxmlLoader = new FXMLLoader(StickHero.class.getResource("HomePage.fxml"));
//                    Parent sc = fxmlLoader.load();
//                    Stage window = new Stage();
//                    Scene home = new Scene(sc);
//                    window.setTitle("codimg");
//                    window.setScene(home);
//                    window.setResizable(false);
//                    window.show();
//
//                    assertNotNull(window); // Verify window creation (optional)
//                } catch (IOException e) {
//                    e.printStackTrace(); // Handle or log the exception
//                }
//            });
//        }
//    }
//
//
//
//
//}
