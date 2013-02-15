package com.gmail.aellondir.dierollerdnd.nethandler.queue;

import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 8
 * @version 0.01
 */
public class PlSendQueue extends Thread {

    private final LinkedTransferQueue<PacketAbs> sQueue = new LinkedTransferQueue<>();
    private volatile boolean run = true;
    private DataOutputStream dOS;

    public PlSendQueue(DataOutputStream dOS) {
        this.dOS = dOS;
    }

    public boolean addToQueue(PacketAbs packet) {
        if (packet.getPacketType() <= 1 && !(packet.getPacketType() <= -1)) {
            return false;
        }

        return this.aTQPrivate(packet);
    }

    private boolean aTQPrivate(PacketAbs packet) {
        synchronized (sQueue) {
            return sQueue.add(packet);
        }
    }

    @Override
    public void run() {
        while (run) {
            if (!(sQueue.isEmpty())) {

                try {
                    sQueue.poll().processSendPacket(dOS);
                } catch (IOException e) {
                    getFrame().errorScreen(e, this);
                }
            }
        }
    }
}
