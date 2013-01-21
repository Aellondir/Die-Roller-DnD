package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;


public class MultiResultsPacket extends Packet {

    public MultiResultsPacket(long sentID) {
        super((byte) 6, sentID);
    }
}
