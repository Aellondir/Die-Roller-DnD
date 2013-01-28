package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 5
 * @version 0.01
 */
public class OutOfSyncPacket extends Packet {

    private long receivedID = 0L;
    private long expectedID = 0L;
    
    private OutOfSyncPacket(long sentID, long receivedID, long expectedID) {
        super((byte) -128, sentID);

        this.receivedID = receivedID;
        this.expectedID = expectedID;
    }

    public static OutOfSyncPacket packetFactory(long sentID, long receivedID, long expectedID) {
        return new OutOfSyncPacket(sentID, receivedID, expectedID);
    }

    public static OutOfSyncPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        long receivedIDR = dIS.readLong();
        long expectedIDR = dIS.readLong();

        return OutOfSyncPacket.packetFactory(sentIDR, receivedIDR, expectedIDR);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeLong(receivedID);
        dOS.writeLong(expectedID);

        dOS.flush();
    }

    public long getExpectedID() {
        return expectedID;
    }

    public long getReceivedID() {
        return receivedID;
    }
}
