package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

public class ConnectPacket extends Packet {
    private boolean isUnTrunc = false;
    private String un;
    private String pW;

    @SuppressWarnings(value="Field")
    private ConnectPacket(String un, String pW, boolean isUnTrunc, long sentID) {
        this.un = un;
        this.pW = pW;
        this.packetType = 1;
        this.sentID = sentID;

        this.isUnTrunc = isUnTrunc;
    }

    public static ConnectPacket cPFactory(String un, String pW, boolean isUnTrunc, long sentID) {
        return new ConnectPacket(un, pW, isUnTrunc, sentID);
    }

    public static ConnectPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        String pWR = dIS.readUTF();
        String unR = dIS.readUTF();
        boolean isUnTruncR = dIS.readBoolean();

        return ConnectPacket.cPFactory(unR, pWR, isUnTruncR, sentIDR);
    }

    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeUTF(pW);
        dOS.writeUTF(un);
        dOS.writeBoolean(isUnTrunc);
    }

    public boolean isIsUnTrunc() {
        return isUnTrunc;
    }

    public String getUn() {
        return un;
    }

    public String getpW() {
        return pW;
    }
}
