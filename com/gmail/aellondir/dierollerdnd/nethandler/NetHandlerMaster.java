package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;

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
        conPl = new HashMap<>();

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

    public String[] getUsernames() {
        String[] unArr = new String[conPl.size()];
        int i = 0;

        for (ConnectedPlayer cP : conPl.values()) {
            unArr[i] = cP.getUN(false);

            i++;
        }

        return unArr;
    }

    public InetAddress getAddress() {
        return address;
    }

    @Override
    public boolean isMaster() {
        return true;
    }

    protected void connectionAccepted(Socket socket, ConnectPacket cP) {
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
                    preFixStr = preFixChar + cP.getUnTrunc();
                }

                boolean isUnSim = conPl.get(cP.getUnTrunc()).getUN(false).equals(cP.getUnFull());

                conPl.put(preFixStr, new ConnectedPlayer(socket, cP.isUnTrunc(), cP.getUnFull(), Integer.toString(preFixChar).charAt(0), isUnSim));

            } else if (!cP.isUnTrunc() && conPl.containsKey(cP.getUnFull())) {
                int preFixChar = 1;
                String preFixStr = Integer.toString(preFixChar) + cP.getUnFull();

                while (conPl.containsKey(preFixStr)) {
                    preFixChar++;
                    preFixStr = preFixChar + cP.getUnFull();
                }

                conPl.put(preFixStr, new ConnectedPlayer(socket, cP.isUnTrunc(), cP.getUnFull(), Integer.toString(preFixChar).charAt(0), true));
            } else {
                conPl.put(cP.getUnTrunc(), new ConnectedPlayer(socket, cP.isUnTrunc(), cP.getUnFull()));
            }

        } catch (IOException e) {
            getFrame().errorScreen(e);
        }
    }

    protected void connectionDenied(Socket socket) {
    }

    protected void callGC() {
        System.gc();
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
                } catch (final IOException e1) {
                    getFrame().errorScreen(e);
                }

                getFrame().errorScreen(e);
            }


        }
    }
}
