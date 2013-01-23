package com.gmail.aellondir.dierollerdnd.nethandler.packet;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 2 implementation 8
 * @version 0.01
 */
public class SendNewPlayerPacket extends Packet {

    public SendNewPlayerPacket(byte packetType, long sentID) {
        super(packetType, sentID);
    }

}
