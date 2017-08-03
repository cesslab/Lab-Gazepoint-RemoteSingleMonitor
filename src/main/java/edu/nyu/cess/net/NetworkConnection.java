package edu.nyu.cess.net;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.net.Socket;

public class NetworkConnection {
    private static Socket socket;
    private static boolean connected;
    private static SocketConnectionTask socketConnectionTask;

    public static boolean isConnected() {
        if (socket != null) {
            return socket.isConnected();
        }
        return false;
    }

    public static void connect(String ipAddress, int port,
                               EventHandler<WorkerStateEvent> onConnect, EventHandler<WorkerStateEvent> onFail) {
        socketConnectionTask = new SocketConnectionTask(ipAddress, port);
        socketConnectionTask.setOnFailed(e -> {
            connected =  false;
            onFail.handle(e);
        });

        socketConnectionTask.setOnSucceeded(e -> {
            connected = true;
            socket =  socketConnectionTask.getValue();
            onConnect.handle(e);
        });
        new Thread(socketConnectionTask).start();
    }

    public static void cancelConnect() {
        if (socketConnectionTask != null) {
            socketConnectionTask.cancel();
        }
    }
}
