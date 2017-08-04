package edu.nyu.cess.controller;

import com.jfoenix.controls.JFXButton;
import edu.nyu.cess.net.CalibrationTask;
import edu.nyu.cess.net.ShowCalibrationScreenTask;
import edu.nyu.cess.scene.SceneName;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CalibrationController implements Updatable, Initializable {
    @FXML
    JFXButton displayScreenButton;

    @FXML
    JFXButton startCalibrationButton;
    @FXML
    JFXButton nextButton;

    @FXML
    Label feedbackLabel;

    private Service showScreenService;
    private Service calibrationService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void onNavigateUpdate(SceneName previousSceneName) {
        if (showScreenService == null) {
            Task<Boolean> showScreenTask = new ShowCalibrationScreenTask();
            showScreenTask.setOnSucceeded(e -> Platform.runLater(this::screenDisplayed));
            showScreenTask.setOnFailed(e -> Platform.runLater(this::displayScreenFailed));
            showScreenService = new Service() {
                @Override
                protected Task<Boolean> createTask() {
                    return showScreenTask;
                }
            };
        }

        if (calibrationService == null) {
            Task<Boolean> calibrationTask = new CalibrationTask();
            calibrationTask.setOnSucceeded(e -> Platform.runLater(this::calibrationSucceeded));
            calibrationTask.setOnFailed(e -> Platform.runLater(this::calibrationFailed));

            calibrationService = new Service() {
                @Override
                protected Task<Boolean> createTask() {
                    return calibrationTask;
                }
            };
        }
    }

    private void handleServiceRequest(Service service) {
        if (service.getState() == Service.State.SUCCEEDED) {
            service.reset();
            service.start();
        }
        else if (service.getState() == Service.State.READY) {
            service.start();
        }
        System.out.println(service.getState().toString());

    }

    @FXML
    public void displayScreenClicked() {
        handleServiceRequest(showScreenService);
    }

    public void screenDisplayed() {
        feedbackLabel.setText("Calibration Screen Displayed");
    }

    public void calibrationSucceeded() {

    }

    public void calibrationFailed() {

    }

    @FXML
    public void calibrateButtonClicked() {
        handleServiceRequest(calibrationService);
    }

    @FXML
    public void displayScreenFailed() {
        feedbackLabel.setText("Failed to Display Calibration Screen");
    }

}
