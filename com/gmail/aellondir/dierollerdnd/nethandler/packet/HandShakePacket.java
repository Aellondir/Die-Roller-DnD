package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 7
 * @version 0.01
 */
public class HandShakePacket extends PacketAbs {

    private String un = null,
            unFull = null,
            pW = null;

    private boolean isUnTrunc = false;

    public HandShakePacket() {
        super((byte) 1, 0L, "GM");
    }

    public HandShakePacket(long sentID, String un, String unFull, String pW) {
        super((byte) 1, sentID, "GM");

        this.un = un;

        this.unFull = unFull;

        if (unFull != null && un.length() < unFull.length()) {
            isUnTrunc = true;
        }
    }

    @Override
    public void processReadPacket(DataInputStream dIS) throws IOException {
        sentID = dIS.readLong();

        un = dIS.readUTF();

        isUnTrunc = dIS.readBoolean();

        if (isUnTrunc) {
            unFull = dIS.readUTF();
        }

        pW = dIS.readUTF();
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeUTF(un);
        dOS.writeBoolean(isUnTrunc);

        if (isUnTrunc) {
            dOS.writeUTF(unFull);
        }

        dOS.writeUTF(pW);

        dOS.flush();
    }

    public String getUn() {
        return un;
    }

    public String getUnFull() {
        return unFull;
    }

    public String getpW() {
        return pW;
    }

    public boolean isIsUnTrunc() {
        return isUnTrunc;
    }
}
