package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 4
 * @version 0.01
 */
public class MultiResultsPacket extends Packet {

    public MultiResultsPacket(long sentID) {
        super((byte) 67, sentID);
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
