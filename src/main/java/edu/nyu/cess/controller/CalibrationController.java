package edu.nyu.cess.controller;

import edu.nyu.cess.concurrent.CalibrationService;
import edu.nyu.cess.scene.SceneName;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CalibrationController implements Updatable, Initializable {
    @FXML
    Button calibrateButton;
    @FXML
    Label statusLabel;

    private CalibrationService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    //    service = new CalibrationService();
    //    statusLabel.textProperty().bind(service.messageProperty());
    }

    @FXML
    public void calibrateButtonClicked() {
//        if (service.getState() == Service.State.SUCCEEDED) {
//            service.reset();
//            service.start();
//        }
//        else if (service.getState() == Service.State.READY) {
//            service.start();
//        }
//        System.out.println(service.getState().toString());
    }

    @Override
    public void onNavigateUpdate(SceneName previousSceneName) {

    }
}
