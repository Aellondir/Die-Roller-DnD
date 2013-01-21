package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

public class RollDicePacket extends Packet {

    public RollDicePacket(long sentID) {
        super((byte) 4, sentID);
    }
}
