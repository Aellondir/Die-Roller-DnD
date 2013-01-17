package com.gmail.aellondir.dierollerdnd.nethandler;

import com.gmail.aellondir.dierollerdnd.nethandler.packet.Packet;
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
    private static Packet[] recievedPackets;

    public ConnectedPlayer(Socket pSocket) throws IOException {
        this.pSocket = pSocket;

        pDIS = new DataInputStream(pSocket.getInputStream());
        pDOS = new DataOutputStream(pSocket.getOutputStream());

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
        while (true) {

        }
    }
}
