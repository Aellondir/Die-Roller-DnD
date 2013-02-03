package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 * Defines the basic operation and architecture of any number of packets.
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 1
 * @version 0.01
 */
public abstract class PacketAbs {

    protected byte packetType = 0;
    protected long sentID;
    protected String recipient = "GM";

    protected PacketAbs() {
        this((byte) 0, 0L, "GM");
    }

    protected PacketAbs(byte packetType, long sentID, String recipient) {
        this.packetType = packetType;
        this.sentID = sentID;
        this.recipient = recipient;
    }

    public abstract void processReadPacket(DataInputStream dIS) throws IOException;

    public abstract void processSendPacket(DataOutputStream dOS) throws IOException;

    public final byte getPacketType() {
        return packetType;
    }

    public final Long getSentID() {
        return sentID;
    }

    public final String getRecipient() {
        return recipient;
    }
}
