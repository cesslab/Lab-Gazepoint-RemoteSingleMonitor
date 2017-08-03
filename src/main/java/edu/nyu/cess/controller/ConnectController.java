package edu.nyu.cess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.nyu.cess.net.NetworkConnection;
import edu.nyu.cess.scene.SceneName;
import edu.nyu.cess.scene.SceneNavigator;
import edu.nyu.cess.ui.MainPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectController implements Initializable, Updatable {
    @FXML
    private JFXButton connectButton;
    @FXML
    private JFXTextField portTextField;
    @FXML
    private JFXTextField ipTextField;

    @Override
    public void onNavigateUpdate(SceneName previousSceneName) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        portTextField.setText("4242");

    }

    @FXML
    public void connectButtonClicked() {
        SceneNavigator.setScene(SceneName.CONNECT_WAIT);
        int port = Integer.valueOf(portTextField.getText());
        String ipAddress = ipTextField.getText();
        NetworkConnection.connect(ipAddress, port, e->connectionSucceeded(), e->connectionFailed());
    }

    private static void connectionFailed() {
        SceneNavigator.setScene(SceneName.CONNECT);
        MainPane.displaySlidingMessage("Connection Failed!");
    }

    private static void connectionSucceeded() {
        SceneNavigator.setScene(SceneName.CALIBRATE);
        MainPane.displaySlidingMessage("Connected.");
    }
}
