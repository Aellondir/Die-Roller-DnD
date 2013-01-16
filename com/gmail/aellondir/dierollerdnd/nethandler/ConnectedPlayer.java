package com.gmail.aellondir.dierollerdnd.nethandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.atomic.AtomicLong;

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
    private AtomicLong recievedID;
    private boolean packetRecieved = false;

    public ConnectedPlayer(Socket pSocket) throws IOException {
        this.pSocket = pSocket;

        pDIS = new DataInputStream(pSocket.getInputStream());
        pDOS = new DataOutputStream(pSocket.getOutputStream());

        recievedID = new AtomicLong(0L);

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

    public boolean hasPacketRecieved() {
        return packetRecieved;
    }

    @Override
    public void run() {
        while (true) {

        }
    }
}
