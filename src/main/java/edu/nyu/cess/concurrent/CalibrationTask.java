package edu.nyu.cess.concurrent;

import edu.nyu.cess.gazepoint.Calibrate;
import edu.nyu.cess.gazepoint.Message;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CalibrationTask extends Task<Void> {
    private Socket socket;

    @Override
    public Void call() {
        try (Socket socket = new Socket("192.168.1.19", 4242)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println(Calibrate.SHOW_MESSAGE);
            String showResponse = reader.readLine();
            if ( ! Message.isResponse(showResponse, Calibrate.SHOW_ID)) {
                updateMessage(String.format("Calibration Failed: %s", showResponse));
                return null;
            }
            else {
                updateMessage("Displaying Calibration Screen...");
            }

            writer.println(Calibrate.START_MESSAGE);
            String startResponse = reader.readLine();
            if ( ! Message.isResponse(startResponse, Calibrate.START_ID)) {
                updateMessage(String.format("Calibration Failed: %s", startResponse));
                return null;
            }
            else {
                updateMessage("Starting Calibration...");
            }

            String message;
            while ((message = reader.readLine()) != null) {
                if (Message.hasIdValue(message, Calibrate.CALIBRATION_RESULT_ID)) {
                    break;
                }
                System.out.println(message);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        updateMessage("Calibration complete...");
        return null;
    }
}
