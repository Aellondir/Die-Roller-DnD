package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2 implementation 3
 * @version 0.01
 */
public class ConnectionDeniedPacket extends Packet {

    public ConnectionDeniedPacket(long sentID) {
        super((byte) 3, sentID);
    }
}
