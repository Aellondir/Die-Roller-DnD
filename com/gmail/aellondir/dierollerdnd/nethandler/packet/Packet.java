package com.gmail.aellondir.dierollerdnd.nethandler.packet;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2
 * @version 0.01
 */
public abstract class Packet {
    protected byte packetType = 0;
    protected long sentID;

    public Packet(byte packetType, long sentID) {
        this.packetType = packetType;
        this.sentID = sentID;
    }

    public byte getPacketType() {
        return packetType;
    }

    public Long getSentID() {
        return sentID;
    }
}
