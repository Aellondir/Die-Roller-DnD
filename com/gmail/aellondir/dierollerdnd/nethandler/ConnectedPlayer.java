package com.gmail.aellondir.dierollerdnd.nethandler;

import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 4
 * @version 0.01
 */
public class ConnectedPlayer extends Thread {
    private Socket pSocket;
    private DataInputStream pDIS;
    private DataOutputStream pDOS;
    private static long recievedID = 0L;
    private static long sentID = 0L;
    private boolean connected;
    private boolean isUNTrunc;

    public ConnectedPlayer(Socket pSocket, boolean isUNTrunc) throws IOException {
        this.pSocket = pSocket;
        this.isUNTrunc = isUNTrunc;

        pDIS = new DataInputStream(pSocket.getInputStream());
        pDOS = new DataOutputStream(pSocket.getOutputStream());

        connected = true;
        this.setDaemon(true);
    }

    public Socket getPSocket() {
        return pSocket;
    }

    public DataInputStream getPDIS() {
        return pDIS;
    }

    public DataOutputStream getPDOS() {
        return pDOS;
    }

    public static long getRecievedID() {
        return recievedID;
    }

    public static long getSentID() {
        return sentID;
    }

    @Override
    public void run() {
        while (connected) {

        }
    }
}
