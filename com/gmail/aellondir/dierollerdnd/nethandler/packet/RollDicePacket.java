package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2 implementation 7
 * @version 0.01
 */
public class RollDicePacket extends Packet {

    public RollDicePacket(long sentID) {
        super((byte) 4, sentID);
    }
}
