package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2 implementation 9
 * @version 0.01
 */
public class ChatPacket extends Packet {

    public ChatPacket(byte packetType, long sentID) {
        super(packetType, sentID);
    }

}
