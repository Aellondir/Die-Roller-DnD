package com.gmail.aellondir.dierollerdnd.nethandler.packet;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2 implementation 10
 * @version 0.01
 */
public class MultiResultsEndPacket extends Packet {

    public MultiResultsEndPacket(byte packetType, long sentID) {
        super(packetType, sentID);
    }

}
