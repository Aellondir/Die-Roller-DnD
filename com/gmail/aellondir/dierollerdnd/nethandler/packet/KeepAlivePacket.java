package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 12
 * @version 0.01
 */
public class KeepAlivePacket extends PacketAbs {

    public KeepAlivePacket() {
        this(0L);
    }

    public KeepAlivePacket(long sentID) {
        super((byte) 4, sentID, "");
    }

    @Override
    public void processReadPacket(DataInputStream dIS) throws IOException {
        dIS.readLong();
        dIS.readUTF();
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeUTF(recipient);

        dOS.flush();
    }
}
