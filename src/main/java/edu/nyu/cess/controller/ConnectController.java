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
import java.util.prefs.Preferences;

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
        Preferences pref = Preferences.userNodeForPackage(ConnectController.class);
        String ipAddress = pref.get("ipAddress", "");
        ipTextField.setText(ipAddress);

        String port = pref.get("port", "4242");
        portTextField.setText(port);
    }

    @FXML
    public void connectButtonClicked() {
        SceneNavigator.setScene(SceneName.CONNECT_WAIT);
        int port = Integer.valueOf(portTextField.getText());
        String ipAddress = ipTextField.getText();
        NetworkConnection.connect(ipAddress, port, e->connectionSucceeded(), e->connectionFailed());
    }

    private void connectionFailed() {
        SceneNavigator.setScene(SceneName.CONNECT);
        MainPane.displaySlidingMessage("Connection Failed!");
    }

    private void connectionSucceeded() {
        SceneNavigator.setScene(SceneName.CALIBRATE);
        MainPane.displaySlidingMessage("Connected.");

        Preferences pref = Preferences.userNodeForPackage(ConnectController.class);
        pref.put("ipAddress", ipTextField.getText());
        pref.put("port", portTextField.getText());
    }
}
