package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2
 * @version 0.01
 */
public abstract class Packet {
    protected byte packetType;
    // if this is the connect packet then this array will be set to one index, set to -1.
    protected Long sentID;

    public abstract Packet processReadPacket(DataInputStream dIS)throws IOException;

    public abstract void processSendPacket(DataOutputStream dOS) throws IOException;
}
