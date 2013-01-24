package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

public class PlayerChangePacket extends Packet {

    public PlayerChangePacket(long sentID) {
        super((byte) 71, sentID);
    }

    public static PlayerChangePacket packetFactory() {
        //@todo Implementation
    }

    public static PlaterChangePacket processReadPacket(DataInputStream dIS) throws IOException {
        //@todo Implementation
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {

    }
}
