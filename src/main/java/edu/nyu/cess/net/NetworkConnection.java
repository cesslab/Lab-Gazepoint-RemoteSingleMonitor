package edu.nyu.cess.net;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkConnection {
    private static Socket socket;
    private static SocketConnectionTask socketConnectionTask;

    static boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public static void connect(String ipAddress, int port,
                               EventHandler<WorkerStateEvent> onConnect, EventHandler<WorkerStateEvent> onFail) {
        socketConnectionTask = new SocketConnectionTask(ipAddress, port);
        socketConnectionTask.setOnFailed(onFail);

        socketConnectionTask.setOnSucceeded(e -> {
            socket =  socketConnectionTask.getValue();
            onConnect.handle(e);
        });
        new Thread(socketConnectionTask).start();
    }

    static PrintWriter getPrintWriter() {
        try {
            return new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static BufferedReader getBufferedReader() {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void cancelConnect() {
        if (socketConnectionTask != null) {
            socketConnectionTask.cancel();
        }
    }

    public static void close() {
        if (socketConnectionTask != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
