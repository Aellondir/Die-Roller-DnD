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
    private String userName;
    private int index;
    private Socket pSocket;
    private DataInputStream pDIS;
    private DataOutputStream pDOS;
    private AtomicLong recievedID;

    public ConnectedPlayer(Socket pSocket, String userName, int index) throws IOException {
        this.pSocket = pSocket;
        this.userName = userName;
        this.index = index;

        pDIS = new DataInputStream(pSocket.getInputStream());
        pDOS = new DataOutputStream(pSocket.getOutputStream());

        recievedID = new AtomicLong(0L);

        this.setDaemon(true);
    }

    public String getUserName() {
        return userName;
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

    @Override
    public void run() {
        while (true) {

        }
    }
}
