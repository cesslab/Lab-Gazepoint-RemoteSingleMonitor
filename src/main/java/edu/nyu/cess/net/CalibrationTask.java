package edu.nyu.cess.net;

import edu.nyu.cess.gazepoint.Calibrate;
import edu.nyu.cess.gazepoint.Message;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CalibrationTask extends Task<Boolean> {

    @Override
    public Boolean call() {
        try {
            PrintWriter writer = NetworkConnection.getPrintWriter();
            BufferedReader reader = NetworkConnection.getBufferedReader();

            writer.println(Calibrate.START_MESSAGE);
            String startResponse = reader.readLine();
            if ( ! Message.isResponse(startResponse, Calibrate.START_ID)) {
                updateMessage(String.format("Calibration Failed: %s", startResponse));
                return false;
            }

            updateMessage("Starting Calibration...");

            String message;
            while ((message = reader.readLine()) != null) {
                if (Message.hasIdValue(message, Calibrate.CALIBRATION_RESULT_ID)) {
                    updateMessage("Calibration complete.");
                    return true;
                }

                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        updateMessage("Calibration failed!");
        return false;
    }
}
