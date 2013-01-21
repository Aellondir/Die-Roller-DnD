package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.Packet;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

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
    private boolean keepAlive = true;
    private PacketHandler pHandler;

    public NetHandlerMaster(String passWord, int expectedConnections) {
        super("MASTER", passWord);
        this.expectedConnections = expectedConnections;
        pHandler = new PacketHandler();

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

    public Set<String> getUsernames() {
        return conPl.keySet();
    }

    public InetAddress getAddress() {
        return address;
    }

    @Override
    public boolean isMaster() {
        return true;
    }

    private void connectionAccepted(String username, Socket socket) {
    }

    @Override
    public void run() {
        int keepAlive = 0;
        while (keepAlive < expectedConnections) {
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

            } catch (final IOException e) {
                getFrame().errorScreen(e);
            }
        }
    }

    private class PacketHandler extends Thread {

        private PriorityQueue<Packet> prQueue;
        private boolean run = true;

        public PacketHandler() {
            prQueue = new PriorityQueue<>();
        }

        public void shutDown() {
            run = false;
        }

        public boolean addToQueue(Packet packet) {
            return prQueue.add(packet);
        }

        @Override
        public void run() {
            do {
                do {
                    if (prQueue.isEmpty()) {
                        break;
                    }

                    //@todo packet handleing logic may be handed off to another class

                } while (run);

                try {
                    Thread.sleep(250L);
                } catch (InterruptedException e) {
                    getFrame().errorScreen(e);
                }
            } while (run);
        }
    }
}
