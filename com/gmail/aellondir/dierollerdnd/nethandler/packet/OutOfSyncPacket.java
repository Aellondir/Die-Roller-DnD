package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.DataInputStream;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 2 implementation 1
 * @version 0.01
 */
public class OutOfSyncPacket extends Packet {

    @Override
    public boolean processReadPacket(DataInputStream dIS) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean processSendPacket(DataInputStream dOS) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
