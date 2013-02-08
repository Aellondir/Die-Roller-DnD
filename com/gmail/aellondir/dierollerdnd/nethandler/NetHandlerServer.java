package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import com.gmail.aellondir.dierollerdnd.nethandler.queue.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.TreeSet;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 3
 * @version 0.01
 */
public class NetHandlerServer extends NetHandlerAbst {

    private static NetHandlerServer nHS;
    private ServerSocket sSocket;
    private InetAddress inAddress;
    private final HashMap<String, ConnectedPlayer> conPl = new HashMap<>();
    private final SerReceiveQueue sRQ = new SerReceiveQueue();
    private volatile int expectedConn;
    private volatile boolean run;

    public NetHandlerServer(String un, String passWord) {
        this(un, passWord, 0, 0);
    }

    public NetHandlerServer(String un, String passWord, int expectedConnections) {
        this(un, passWord, 0, expectedConnections);
    }

    public NetHandlerServer(String un, String passWord, int port, int expectedConn) {
        super(un, passWord);
        this.setDaemon(true);

        this.expectedConn = expectedConn;

        try {
            this.sSocket = new ServerSocket(port);
        } catch (final IOException e) {
            getFrame().errorScreen(e, this.nonStaticGetNHS());

            return;
        }


        inAddress = sSocket.getInetAddress();

        if (this.nonStaticGetNHS() != null && sSocket != null && sSocket.isBound()) {
            run = true;
        }
    }

    @Override
    public void updateJCBPlayers() {
        synchronized (conPl) {
            TreeSet<String> tS = new TreeSet(conPl.keySet());

            getFrame().updateJCBPlayers(tS);
        }
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

    public InetAddress getInetAddress() {
        return inAddress;
    }

    private void sendAllPlayers(PacketAbs packet) {
        for (ConnectedPlayer cPl: conPl.values()) {
            cPl.addToSendQueue(packet);
        }
    }

    private void checkConPls() {
        synchronized (conPl) {
            for (ConnectedPlayer cPl : conPl.values()) {
                if (cPl.isConnected()) {
                    continue;
                } else {
                    try {
                        cPl.shutdown();
                    } catch (IOException e) {
                        getFrame().errorScreen(e, this);
                    }
                }
            }
        }
    }

    @Override
    public void connectionAccepted(Socket socket, HandShakePacket packet) {
        synchronized (conPl) {
            try {
                if (conPl.isEmpty()) {
                    if (packet.isIsUnTrunc()) {
                        conPl.put(packet.getUn(), new ConnectedPlayer(packet.getUnFull(), socket, 0));
                    }
                } else {
                    int preFix = 0;

                    while (conPl.containsKey((Integer.toString(preFix) + packet.getUn()))) {
                        preFix++;
                    }

                    if (preFix > 0) {
                        conPl.put(Integer.toString(preFix) + packet.getUn(), new ConnectedPlayer(packet.getUnFull(), socket, preFix));
                    } else {
                        conPl.put(packet.getUn(), new ConnectedPlayer(packet.getUnFull(), socket, 0));
                    }
                }
            } catch (IOException e) {
                getFrame().errorScreen(e, this);
            }
        }
    }

    @Override
    public boolean isServer() {
        return true;
    }

    @Override
    public final void shutDown() {
        run = false;
        expectedConn = 0;

        synchronized (this) {
            this.notify();
        }

        //@todo other shutdown methods.
    }

    @Override
    public void run() {
        do {
            this.checkConPls();

            while (conPl.isEmpty() || conPl.size() < expectedConn) {
                try {
                    Socket nSocket = sSocket.accept();

                    if ((new DataInputStream(nSocket.getInputStream())).readByte() == 1) {
                        AuthenticateConnection aC = new AuthenticateConnection(nSocket, conPl.size());

                        aC.start();
                    }
                } catch (IOException e) {
                    getFrame().errorScreen(e, this.nonStaticGetNHS());
                }
            }

            synchronized (this) {
                try {
                    this.wait(30000L);
                } catch (InterruptedException e) {
                    getFrame().errorScreen(e, this);
                }
            }

            this.sendAllPlayers(new KeepAlivePacket());
        } while (run);
    }
}
