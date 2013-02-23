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
    private Integer prefix = null;
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
        if (tOrFull && this.isUNTrunc() && prefix != null) {
            return prefix.toString() + unTrunc;
        } else if (prefix != null) {
            return prefix.toString() + unFull;
        } else if (tOrFull && this.isUNTrunc()) {
            return this.unTrunc;
        } else {
            return unFull;
        }
    }

    @Override
    public final String truncUN(String un) {
        String truncationStr = un;

        if (truncationStr.length() > 16) {
            truncationStr = "";

            for (byte b : un.getBytes(cs)) {
                truncationStr += (char) b;
            }

            return truncationStr;
        } else {
            return null;
        }
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
            try {
                if (pDis.available() > 0) {
                    byte b = pDis.readByte();

                    PacketAbs pA = PacketFactory.getInstance().getByPacketType(b);

                    pA.processReadPacket(pDis);

                    int check = plRQ.addToQueue(pA);


                    if (check == 1) {
                        //@todo check packet logic/method.
                    } else if (check == 2) {
                       //@todo tryBlockingAdd
                    } else if (check == 3) {
                        //@todo FUCK.
                    }
                }
            } catch (IOException | InterruptedException e) {
                if (e instanceof InterruptedException) {
                    InterruptedException internalE = new InterruptedException(e.getMessage() + " So yeah that happended... MAKE SURE YOU SEND ME THIS!!!");

                    internalE.setStackTrace(e.getStackTrace());

                    getFrame().errorScreen(internalE, this);
                }

                else {
                    getFrame().errorScreen(e, this);
                }
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

        private int addToQueue(PacketAbs packet) {
            if (packet.getPacketType() <= 1 && !(packet.getPacketType() <= -1)) {
                return 1;
            }

            synchronized (sQueue) {
                return sQueue.offer(packet, 1000L, TimeUnit.MILLISECONDS) ? 0 : 2;
            }
        }

        private int tryBlockingAdd() {
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

    private class PlReceivedQueue extends Thread {

        private final LinkedBlockingQueue<PacketAbs> rQueue = new LinkedBlockingQueue<>();
        private volatile boolean run = true;

        public PlReceivedQueue() {
            super(playerThreads, unTrunc + ":Received Queue");
        }

        private int addToQueue(PacketAbs packet) throws InterruptedException {
            if (packet.getPacketType() < 2 && !(packet.getPacketType() <= -1)) {
                return 1;
            }

            synchronized (rQueue) {
                return rQueue.offer(packet, 1000L, TimeUnit.MILLISECONDS) ? 0 : 2;
            }
        }

        private int tryBlockingAdd(PacketAbs packet) {
            return -1;
        }

        @Override
        public void run() {
            while (run) {
                if (!rQueue.isEmpty()) {
                    //@todo send packets to where they belong.
                }
            }
        }
    }
}
