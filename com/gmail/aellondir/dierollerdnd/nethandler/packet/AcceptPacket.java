package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class AcceptPacket extends PacketAbs {

    public AcceptPacket() {
        this(0L);
    }

    public AcceptPacket(long sentID) {
        super((byte) 2, sentID, "");
    }

    @Override
    public void processReadPacket(DataInputStream dIS) throws IOException {
        sentID = dIS.readLong();
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
