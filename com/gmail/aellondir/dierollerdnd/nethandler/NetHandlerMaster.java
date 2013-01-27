package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Handles the master side of the relationship between die rollers.
 *
 * @author James Hull
 * @serial JPGH.0001 class 3 implementation 1
 * @version 0.01
 */
public class NetHandlerMaster extends NetHandler {

    private int expectedConnections;
    private ServerSocket masSocket;
    private InetAddress address;
    private HashMap<String, ConnectedPlayer> conPl;
    private PacketHandler pHandler;

    public NetHandlerMaster(String passWord, int expectedConnections) {
        super("MASTER", passWord);
        this.expectedConnections = expectedConnections;
        pHandler = new PacketHandler();
        conPl = new HashMap<>(expectedConnections);

        try {
            masSocket = new ServerSocket(0);
        } catch (final IOException e) {

            if (masSocket != null) {
                try {
                    masSocket.close();

                    masSocket = null;
                } catch (final IOException e1) {
                    getFrame().errorScreen(e1);
                }
            }

            getFrame().errorScreen(e);
        }

        address = masSocket.getInetAddress();

        this.setDaemon(true);
    }

    public TreeSet<String> getUsernames() {
        TreeSet<String> unTSet = new TreeSet<>(conPl.keySet());

        return unTSet;
    }

    public InetAddress getAddress() {
        return address;
    }

    @Override
    public boolean isMaster() {
        return true;
    }

    @Override
    public void shutDown() {
        //@todo shutdown methodology.
    }

    protected void connectionAccepted(Socket socket, HandshakePacket cP) {
        if (!pHandler.isAlive()) {
            pHandler.start();
        }

        try {
            if (conPl.isEmpty()) {

                conPl.put(cP.getUnTrunc(), new ConnectedPlayer(socket, cP.isUnTrunc(), cP.getUnFull()));

            } else if (cP.isUnTrunc() && conPl.containsKey(cP.getUnTrunc())) {
                int preFixChar = 1;
                String preFixStr = Integer.toString(preFixChar) + cP.getUnTrunc();

                while (conPl.containsKey(preFixStr)) {
                    preFixChar++;
                    preFixStr = Integer.toString(preFixChar) + cP.getUnTrunc();
                }

                boolean isUnSim = conPl.get(cP.getUnTrunc()).getUN(false).equals(cP.getUnFull());

                conPl.put(preFixStr, new ConnectedPlayer(socket, cP.isUnTrunc(), cP.getUnFull(), Integer.toString(preFixChar).charAt(0), isUnSim));

            } else if (!cP.isUnTrunc() && conPl.containsKey(cP.getUnFull())) {
                int preFixChar = 1;
                String preFixStr = Integer.toString(preFixChar) + cP.getUnFull();

                while (conPl.containsKey(preFixStr)) {
                    preFixChar++;
                    preFixStr = Integer.toString(preFixChar) + cP.getUnFull();
                }

                conPl.put(preFixStr, new ConnectedPlayer(socket, cP.isUnTrunc(), cP.getUnFull(), Integer.toString(preFixChar).charAt(0), true));
            } else {
                conPl.put(cP.getUnTrunc(), new ConnectedPlayer(socket, cP.isUnTrunc(), cP.getUnFull()));
            }
        } catch (IOException e) {
            getFrame().errorScreen(e);
        }

        conPl.get(cP.getUnTrunc()).start();

        getFrame().updateJCB();
    }

    protected void connectionDenied(Socket socket) {
    }

    private void sendAllPlayers() {

    }

    @Override
    public void run() {
        int keepAlive = 0;
        while (keepAlive < expectedConnections) {
            try (Socket slave = masSocket.accept();) {
                if (slave != null) {
                    AuthenticateConnection aC = new AuthenticateConnection(slave, this);

                    aC.start();
                }

            } catch (final IOException e) {
                try {
                    masSocket.close();

                    masSocket = null;
                } catch (final IOException e1) {
                    getFrame().errorScreen(e1);
                }

                getFrame().errorScreen(e);
            }
        }
    }
}
