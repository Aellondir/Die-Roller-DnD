package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 * Defines the basic operation and architecture of any number of packets.
 *
 * @author James Hull
 * @serial JPGH.0001 class 6
 * @version 0.01
 */
public class Packet {
    /****************************************************************************
     * PAY ATTENTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * @TODO Make no arg constructors IN ALL of the packet subclasses the only
     * thing they will do is the packet type. THEN CHANGE ALL INSTANCES OF THE
     * PROCESS READ PACKET METHOD TO NON-STATIC, AND PLACE AN ABSTRACT METHOD FOR
     * IT IN THIS CLASS!
     *
     * IF YA SMEEEEEEEEEELL WHAT THE PRE-BEDTIME GUY IS COOKING!
     *
     * Foley is god, Ditka can suck my dick-ah.
     */
    protected byte packetType = 0;
    protected long sentID;

    public Packet(byte packetType, long sentID) {
        this.packetType = packetType;
        this.sentID = sentID;
    }

    public void processSendPacket(DataOutputStream dOS) throws IOException {
        throw new UnsupportedOperationException("This should never be thrown.");
    }

    public final byte getPacketType() {
        return packetType;
    }

    public final Long getSentID() {
        return sentID;
    }
}
