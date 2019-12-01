package edu.ib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FXChart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("/fxml/chart.fxml"));
        Scene scene= new Scene(root,700,450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My app - planets");
        primaryStage.show();


    }
}
