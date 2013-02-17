package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.interfaces.GeneralNetInterface;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 5
 * @version 0.01
 */
public class ConnectedPlayer extends Thread implements GeneralNetInterface {

    private String unTrunc = null,
            unFull;
    private Integer preFix = null;
    private Socket pSocket;
    private DataInputStream pDis;
    private DataOutputStream pDos;
    private final PlReceivedQueue plRQ = new PlReceivedQueue();
    private final PlSendQueue plSQ = new PlSendQueue();
    private volatile boolean run = false;

    public ConnectedPlayer(String unFull, Socket pSocket, int preFix) throws IOException {
        super(playerThreads, (unFull + " :DieRoller Player Thread"));

        this.unFull = unFull;

        this.unTrunc = this.truncUN(unFull);

        this.pSocket = pSocket;

        this.pDis = new DataInputStream(pSocket.getInputStream());
        this.pDos = new DataOutputStream(pSocket.getOutputStream());

        run = true;
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

            for (byte b : un.getBytes(cs)) {
                truncationStr += (char) b;
            }
        }

        return truncationStr;
    }

    public boolean isUNTrunc() {
        return unTrunc != null;
    }

    public void shutdown() throws IOException {
    }

    public void addToSendQueue(PacketAbs packet) {
        synchronized (plSQ) {
            this.plSQ.addToQueue(packet);
        }
    }

    protected boolean isConnected() {
        return (pSocket.isConnected() && !pSocket.isClosed());
    }

    @Override
    public void run() {
        while (run) {
            if (pDis.available() > 0) {

            }

            plSQ.trySend();
        }
    }

    private class PlSendQueue extends Thread {

        private final LinkedTransferQueue<PacketAbs> sQueue = new LinkedTransferQueue<>();
        private volatile boolean run = true, send = false;

        private PlSendQueue() {
           super(playerThreads, unTrunc + ":Send Queue");
        }

        private boolean addToQueue(PacketAbs packet) {
            if (packet.getPacketType() <= 1 && !(packet.getPacketType() <= -1)) {
                return false;
            }

            synchronized (sQueue) {
                return sQueue.add(packet);
            }
        }

        private void trySend() {
            send = true;
        }

        @Override
        public void run() {
            while (run) {
                if (send && !(sQueue.isEmpty())) {

                    try {
                        sQueue.poll().processSendPacket(pDos);
                    } catch (IOException e) {
                        getFrame().errorScreen(e, this);
                    }
                } else if (sQueue.isEmpty()) {
                    send = false;
                }
            }
        }
    }

    private class PlReceivedQueue {

        private final LinkedBlockingQueue<PacketAbs> rQueue = new LinkedBlockingQueue<>();
        private volatile boolean run = true;


    }
}
