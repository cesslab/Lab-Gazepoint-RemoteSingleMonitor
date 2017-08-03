package edu.nyu.cess;

import edu.nyu.cess.scene.SceneName;
import edu.nyu.cess.ui.MainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new MainPane(SceneName.CONNECT)));
        primaryStage.show();
        primaryStage.setTitle("CESS Eye Tracker Controller");
        primaryStage.show();
    }
}
