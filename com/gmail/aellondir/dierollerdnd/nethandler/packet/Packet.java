package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2
 * @version 0.01
 */
public abstract class Packet {
    protected int sentBy;
    protected long packetSendID;
    protected int packetID;

    public int getPacketID() {
        return packetID;
    }

    public abstract boolean processReadPacket(DataInputStream dIS);

    public abstract boolean processSendPacket(DataOutputStream dOS);
}
