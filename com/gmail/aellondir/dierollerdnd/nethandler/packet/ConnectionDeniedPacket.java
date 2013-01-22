package com.gmail.aellondir.dierollerdnd.nethandler.packet;


public class ConnectionDeniedPacket extends Packet {

    public ConnectionDeniedPacket(long sentID) {
        super((byte) 3, sentID);
    }
}
