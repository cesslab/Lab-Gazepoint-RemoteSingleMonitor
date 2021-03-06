package edu.nyu.cess.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.nyu.cess.net.NetworkConnection;
import edu.nyu.cess.scene.SceneName;
import edu.nyu.cess.scene.SceneNavigator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class ConnectController implements Initializable, Swappable {
    @FXML
    private JFXButton connectButton;
    @FXML
    private JFXTextField portTextField;
    @FXML
    private JFXTextField ipTextField;

    @Override
    public void onLoad(SceneName previousSceneName) {

    }

    @Override
    public void onUnload() {

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
    }

    private void connectionSucceeded() {
        SceneNavigator.setScene(SceneName.CALIBRATE);

        Preferences pref = Preferences.userNodeForPackage(ConnectController.class);
        pref.put("ipAddress", ipTextField.getText());
        pref.put("port", portTextField.getText());
    }

}
