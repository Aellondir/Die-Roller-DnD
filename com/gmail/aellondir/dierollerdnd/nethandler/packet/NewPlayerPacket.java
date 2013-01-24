package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;
import java.util.TreeSet;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 8
 * @version 0.01
 */
public class NewPlayerPacket extends Packet {

    private NewPlayerPacket(long sentID) {
        super((byte) 64, sentID);
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
