package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.gui.RollerFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jameshull
 * @serial JPGH.0001 class 5
 * @version 0.01
 */
public class PacketHandler extends Thread {

    private LinkedBlockingQueue<Packet> packetLBQ;
    private long sentID,
            receivedID;
    private boolean run = true;

    public PacketHandler() {
        packetLBQ = new LinkedBlockingQueue<>(127);

        sentID = 0;
        receivedID = 1;
    }

    public void shutDown() {
        run = false;
    }

    public boolean tryEasyAdd(Packet packet) {
        return packetLBQ.offer(packet);
    }

    public boolean tryOtherAdd(Packet packet) {
        try {
            return packetLBQ.offer(packet, 20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            getFrame().errorScreen(e);
        }

        return false;
    }

    public long getSentID() {
        return sentID;
    }

    public long getReceivedID() {
        return receivedID;
    }

    public long getThenIncSentID() {
        return sentID++;
    }

    public long getThenIncReceivedID() {
        return receivedID++;
    }

    @Override
    public void run() {
        while (run) {
            try {
                Packet packet = packetLBQ.take();

                if (packet.getPacketType() < 0) {
                    OutOfSyncPacket oOSP = (OutOfSyncPacket) packet;
                    //-1 = network error
                    //0 = sender fault, in effect this machine.
                    //1 = receiver fault, in effect the machine being communicated to.
                    byte fault;

                    if (oOSP.getExpectedID() > sentID && oOSP.getExpectedID() - sentID > 0) {

                    }

                } else if (packet.getPacketType() > 0 && packet.getPacketType() <= 63) {

                } else if (packet.getPacketType() > 63 && packet.getPacketType() <= 127) {

                } else if (packet.getPacketType() == 0) {
                    throw new Exception("Somehow a packet that should be impossible to send was sent" + packet.getClass());
                }
            } catch (InterruptedException e) {
                getFrame().errorScreen(e);
            } catch (Exception e) {
                getFrame().errorScreen(e);
            }
        }
    }
}
