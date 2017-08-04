package edu.nyu.cess.net;

import edu.nyu.cess.gazepoint.Calibrate;
import edu.nyu.cess.gazepoint.Message;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ShowCalibrationScreenTask extends Task<Boolean> {
    @Override
    protected Boolean call() throws Exception {
        if (! NetworkConnection.isConnected()) {
            updateMessage("Failed to Open Screen.");
            return false;
        }

        PrintWriter printWriter = NetworkConnection.getPrintWriter();
        BufferedReader bufferedReader = NetworkConnection.getBufferedReader();

        if (printWriter == null || bufferedReader == null) {
            updateMessage("Failed to Open Screen.");
            return false;
        }

        printWriter.println(Calibrate.SHOW_SCREEN_MESSAGE);
        String showResponse = bufferedReader.readLine();
        if (Message.isResponse(showResponse, Calibrate.SHOW_SCREEN_ID)) {
            updateMessage("Screen Displayed");
            return true;
        }

        updateMessage("Failed to Open Screen.");
        return false;
    }
}
