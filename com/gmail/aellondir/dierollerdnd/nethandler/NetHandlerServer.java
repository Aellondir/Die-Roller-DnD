package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.TreeSet;

public class NetHandlerServer extends NetHandlerAbst {

    private static NetHandlerServer nHS;
    private ServerSocket sSocket;
    private HashMap<String, ConnectedPlayer> conPl;
    private int expectedConn;

    public NetHandlerServer(String un, String passWord) {
        this(un, passWord, 0, 0);
    }

    public NetHandlerServer(String un, String passWord, int expectedConnections) {
        this(un, passWord, 0, expectedConnections);
    }

    public NetHandlerServer(String un, String passWord, int port, int expectedConn) {
        super(un, passWord);

        this.expectedConn = expectedConn;

        try {
            this.sSocket = new ServerSocket(port);
        } catch (final IOException e) {
            getFrame().errorScreen(e, nonStaticGetNHS());

            return;
        }

        conPl = new HashMap<>(expectedConn);
    }

    @Override
    public synchronized void updateJCBPlayers() {
        TreeSet<String> tS = new TreeSet(conPl.keySet());

        getFrame().updateJCBPlayers(tS);
    }

    private NetHandlerServer nonStaticGetNHS() {
        if (nHS == null) {
            nHS = this;
        }

        return nHS;
    }

    public static NetHandlerServer getNHS() {
        return nHS;
    }

    @Override
    public synchronized void connectionAccepted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isServer() {
        return true;
    }

    @Override
    public final synchronized void shutDown() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {

    }
}
