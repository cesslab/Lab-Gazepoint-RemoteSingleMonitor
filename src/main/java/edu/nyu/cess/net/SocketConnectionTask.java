package edu.nyu.cess.net;

import javafx.concurrent.Task;

import java.net.Socket;

public class SocketConnectionTask extends Task<Socket> {
    private String ipAddress;
    private int portNumber;
    public SocketConnectionTask(String ipAddress, int portNumber) {
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    @Override
    protected Socket call() throws Exception {
        return new Socket(ipAddress, portNumber);
    }
}
