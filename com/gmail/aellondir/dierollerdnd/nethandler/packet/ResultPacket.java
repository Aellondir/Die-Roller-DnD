package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;


public class ResultPacket extends Packet {

    public ResultPacket(long sentID) {
        super((byte) 5, sentID);
    }
}
