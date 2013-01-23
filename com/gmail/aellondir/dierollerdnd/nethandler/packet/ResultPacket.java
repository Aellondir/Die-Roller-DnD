package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2 implementation 6
 * @version 0.01
 */
public class ResultPacket extends Packet {

    public ResultPacket(long sentID) {
        super((byte) 5, sentID);
    }
}
