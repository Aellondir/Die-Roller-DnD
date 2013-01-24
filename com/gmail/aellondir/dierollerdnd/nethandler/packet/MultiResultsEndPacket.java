package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 10
 * @version 0.01
 */
public class MultiResultsEndPacket extends Packet {

    public MultiResultsEndPacket(byte packetType, long sentID) {
        super((byte) 68, sentID);
    }

    public static Packet packetFactory() {
        //@todo Implementation
    }

    public static Packet processReadPacket(DataInputStream dIS) throws IOException {
        //@todo Implementation
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {

    }
}
