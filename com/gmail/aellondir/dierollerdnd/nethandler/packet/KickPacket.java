package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

public class KickPacket extends Packet {

    private final String[] REASON_ARRAY = {"Because fuck you thats why!", "The GM dislikes you."};
    private byte index = 0;
    private String gMDefinedReason = null;

    private KickPacket(long sentID, byte reason, String gMDefinedReason) {
        super((byte) 70, sentID);

        //if gMDefinedReason is not null this will still be sent and kept it just won't be used.
        this.index = reason;
        // should either be set to a string or a null reference.
        this.gMDefinedReason = gMDefinedReason;
    }

    public static KickPacket packetFactory(long sentID, byte reason, String gMDefinedReason) {
        return new KickPacket(sentID, reason, gMDefinedReason);
    }

    public static KickPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        String gMDefReason = null;
        byte reasonR = 0;

        if (dIS.readBoolean()) {
            gMDefReason = dIS.readUTF();
        }
        reasonR = dIS.readByte();


        return KickPacket.packetFactory(sentIDR, reasonR, gMDefReason);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);

        if (gMDefinedReason != null) {
            dOS.writeBoolean(true);

            dOS.writeUTF(gMDefinedReason);
        } else {
            dOS.writeBoolean(false);
        }

        dOS.writeByte(index);

        dOS.flush();
    }

    public String getReason() {
        if (gMDefinedReason == null) {
            return REASON_ARRAY[index];
        } else {
            return gMDefinedReason;
        }
    }
}
