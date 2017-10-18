package edu.nyu.cess;

import edu.nyu.cess.net.NetworkConnection;
import edu.nyu.cess.scene.SceneName;
import edu.nyu.cess.scene.SceneNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HashMap<SceneName, String> scenes = new HashMap<>();
        scenes.put(SceneName.CONNECT, "/connect.fxml");
        scenes.put(SceneName.CALIBRATE, "/calibrate.fxml");
        scenes.put(SceneName.CONNECT_WAIT, "/wait.fxml");

        primaryStage.setOnCloseRequest(e -> NetworkConnection.close());
        SceneNavigator.initialize(primaryStage, scenes);
        SceneNavigator.setScene(SceneName.CONNECT);
        SceneNavigator.show();
    }
}
