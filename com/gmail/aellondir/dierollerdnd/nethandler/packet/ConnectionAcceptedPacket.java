package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;


public class ConnectionAcceptedPacket extends Packet {

    public ConnectionAcceptedPacket(long sentID) {
        super((byte) 2, sentID);
    }

}
