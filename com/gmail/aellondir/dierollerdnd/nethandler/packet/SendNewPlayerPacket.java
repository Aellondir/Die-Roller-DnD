package com.gmail.aellondir.dierollerdnd.nethandler.packet;


public class SendNewPlayerPacket extends Packet {

    public SendNewPlayerPacket(byte packetType, long sentID) {
        super(packetType, sentID);
    }

}
