package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class DeniedPacket extends PacketAbs {

    private final String[] REASONS = {"The GM doesn't like you.",
        "Password fail.",
        "This program fucked up it should crash out in 5... 4... 3... 2... 1..."};

    private byte index = 0;

    public DeniedPacket() {
        this(0L, (byte) 0);
    }

    public DeniedPacket(long sentID, byte index) {
        super((byte) 3, sentID, "");

        this.index = index;
    }

    @Override
    public void processReadPacket(DataInputStream dIS) throws IOException {
        sentID = dIS.readLong();
        recipient = dIS.readUTF();
        index = dIS.readByte();
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(index);
        dOS.writeLong(sentID);
        dOS.writeByte(index);

        dOS.flush();
    }

    public String getReason() {
        return REASONS[index];
    }
}
