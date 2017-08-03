package edu.nyu.cess.concurrent;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.net.Socket;

public class CalibrationService extends Service<Void> {
    private Socket socket;

    @Override
    protected Task<Void> createTask() {
        return new CalibrationTask();
    }
}
