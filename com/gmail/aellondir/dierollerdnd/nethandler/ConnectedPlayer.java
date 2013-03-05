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

    private String unTrunc,
            unFull;
    private Integer prefix = null;
    private Socket pSocket;
    private DataInputStream pDis;
    private DataOutputStream pDos;
    private final PlReceivedQueue plRQ = new PlReceivedQueue();
    private final PlSendQueue plSQ = new PlSendQueue();
    private volatile boolean run = false;

    public ConnectedPlayer(String unFull, Socket pSocket, int prefix) throws IOException {
        super(playerThreads, (unFull + " :DieRoller Player Thread"));

        this.unFull = unFull;

        this.unTrunc = this.truncUN(unFull);

        this.prefix = new Integer(prefix);

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
            try {
                int check = this.plSQ.addToQueue(packet);

                if (check == 1) {
                    // only two packets should return this; handshake or PacketAbs, if an abstract packet is the cause shits
                    // fucked and an exception will be raised, if its the other variety a handshake... shits really fucked and an exception will be raised.

                    switch (packet.getPacketType()) {
                        case 0:
                            throw new UnsupportedOperationException("An abstract packet somehow made it here this is wrong... and in theory impossible");
                        case 1:
                            throw new UnsupportedOperationException("A handshake packet should not be sent at this point lets"
                                    + " try to figure out how it was sent.");
                        default:
                            throw new Exception("Soooooooo... something went wrong and I am not sure what it is.  The packet that got us here was "
                                    + Integer.toString(packet.getPacketType()));
                    }

                } else if (check == 2) {
                    plRQ.tryBlockingAdd(packet);
                }
            } catch (IOException | InterruptedException e) {
                if (e instanceof InterruptedException) {
                    InterruptedException internalE = new InterruptedException(e.getMessage() + "  So yeah that happended... MAKE SURE YOU SEND ME THIS!!!");

                    internalE.setStackTrace(e.getStackTrace());

                    getFrame().errorScreen(internalE, this);
                } else {
                    getFrame().errorScreen(e, this);
                }
            } catch (UnsupportedOperationException e) {
                getFrame().errorScreen(e, this);
            } catch (Exception e) {
                getFrame().errorScreen(e, this);
            }
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
                        // only two packets should return this; handshake or PacketAbs, if an abstract packet is the cause shits
                        // fucked and an exception will be raised, if its the other variety a handshake... shits really fucked and an exception will be raised.

                        switch (pA.getPacketType()) {
                            case 0:
                                throw new UnsupportedOperationException("An abstract packet somehow made it here this is wrong... and in theory impossible");
                            case 1:
                                throw new UnsupportedOperationException("A handshake packet should not be sent at this point lets"
                                        + " try to figure out how it was sent.");
                            default:
                                throw new Exception("Soooooooo... something went wrong and I am not sure what it is.  The packet that got us here was "
                                        + Integer.toString(pA.getPacketType()));
                        }
                    } else if (check == 2) {
                        plRQ.tryBlockingAdd(pA);
                    }
                }
            } catch (IOException | InterruptedException e) {
                if (e instanceof InterruptedException) {
                    InterruptedException internalE = new InterruptedException(e.getMessage() + "  So yeah that happended... MAKE SURE YOU SEND ME THIS!!!");

                    internalE.setStackTrace(e.getStackTrace());

                    getFrame().errorScreen(internalE, this);
                } else {
                    getFrame().errorScreen(e, this);
                }
            } catch (UnsupportedOperationException e) {
                getFrame().errorScreen(e, this);
            } catch (Exception e) {
                getFrame().errorScreen(e, this);
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

        private boolean tryBlockingAdd(PacketAbs packet) {
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

        private boolean tryBlockingAdd(PacketAbs packet) {
            synchronized (rQueue) {
                return rQueue.add(packet);
            }
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
