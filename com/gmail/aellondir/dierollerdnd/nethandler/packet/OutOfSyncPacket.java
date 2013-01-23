package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 2 implementation 5
 * @version 0.01
 */
public class OutOfSyncPacket extends Packet {
    public OutOfSyncPacket(long sentID) {
        super((byte) 127, sentID);
    }
}
