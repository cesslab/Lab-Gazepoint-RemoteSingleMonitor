package edu.nyu.cess.controller;

import com.jfoenix.controls.JFXButton;
import edu.nyu.cess.net.NetworkConnection;
import edu.nyu.cess.scene.SceneName;
import edu.nyu.cess.scene.SceneNavigator;
import edu.nyu.cess.ui.MainPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectWaitController implements Updatable, Initializable{
    @FXML
    private JFXButton cancelButton;

    @FXML
    public void cancelButtonClicked() {
        SceneNavigator.setScene(SceneName.CONNECT);
        MainPane.displaySlidingMessage("Connection Canceled.");
        NetworkConnection.cancelConnect();
    }

    @Override
    public void onNavigateUpdate(SceneName previousSceneName) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
