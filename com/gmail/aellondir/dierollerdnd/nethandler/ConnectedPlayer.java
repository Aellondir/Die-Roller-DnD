package com.gmail.aellondir.dierollerdnd.nethandler;

import com.gmail.aellondir.dierollerdnd.nethandler.queue.PlReceivedQueue;
import com.gmail.aellondir.dierollerdnd.nethandler.queue.PlSendQueue;
import com.gmail.aellondir.dierollerdnd.nethandler.interfaces.GeneralNetInterface;
import java.io.*;
import java.net.*;

public class ConnectedPlayer implements GeneralNetInterface {

    private String unTrunc = null,
            unFull;

    private Socket pSocket;

    private DataInputStream pDis;
    private DataOutputStream pDos;

    private PlReceivedQueue plRQ;
    private PlSendQueue plSQ;


    public ConnectedPlayer(String unFull, Socket pSocket) throws IOException {
        this.unFull = unFull;

        this.unTrunc = this.truncUN(unFull);

        this.pSocket = pSocket;

        this.pDis = new DataInputStream(pSocket.getInputStream());
        this.pDos = new DataOutputStream(pSocket.getOutputStream());
    }

    @Override
    public String getUN(boolean tOrFull) {
        if (tOrFull && this.isUNTrunc()) {
            return this.unTrunc;
        }

        return unFull;
    }

    @Override
    public final String truncUN(String un) {
        String truncationStr = un;

        if (truncationStr.length() > 16) {
            truncationStr = "";

            for (byte b: un.getBytes(cs)) {
                truncationStr += (char) b;
            }
        }

        return truncationStr;
    }

    public boolean isUNTrunc() {
        return unTrunc != null;
    }
}
