package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2 implementation 4
 * @version 0.01
 */
public class MultiResultsPacket extends Packet {

    public MultiResultsPacket(long sentID) {
        super((byte) 6, sentID);
    }
}
