package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.Packet;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;
/**
 *Handles the master side of the relationship between die rollers.
 *
 * @author James Hull
 * @serial JPGH.0001 class 3 implementation 1
 * @version 0.01
 */
public class NetHandlerMaster extends NetHandler implements Runnable {

    private int expectedConnections;
    private ServerSocket masSocket;
    private HashMap<String, ConnectedPlayer> playerSockets;
    private boolean keepAlive = true;

    public NetHandlerMaster(String passWord, int expectedConnections) {
        super("MASTER", passWord);
        this.expectedConnections = expectedConnections;

        try {
            masSocket = new ServerSocket(0);
        } catch (final IOException e) {
            getFrame().errorScreen(e);
        }
    }

    public Set<String> getUsernames() {
        return playerSockets.keySet();
    }

    @Override
    public boolean isMaster() {
        return true;
    }

    public final int packetRecieved(final Packet paramPacket) {

        return paramPacket.getPacketID();
    }

    private void connectionAccepted(String username, Socket socket) {

    }

    @Override
    public void run() {
        while (keepAlive) {
            try (Socket slave = masSocket.accept();) {
            if (slave != null) {
                AuthenticateConnection aC = new AuthenticateConnection(slave);

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

    private class AuthenticateConnection extends Thread {
        private Socket socket;

        public AuthenticateConnection(Socket socket) throws IOException {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (DataInputStream dIS = new DataInputStream(socket.getInputStream()); DataOutputStream dOS = new DataOutputStream(socket.getOutputStream())) {
                String str = dIS.readUTF();

                if (str.equals(getPW())) {

                }

            } catch (final IOException e) {
                getFrame().errorScreen(e);
            }
        }
    }

    private class PacketHandler extends Thread {
        private PriorityQueue<Packet> prQueue;

        public PacketHandler() {
            prQueue = new PriorityQueue<>();
        }
    }
}
