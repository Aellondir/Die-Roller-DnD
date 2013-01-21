package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

public class ConnectPacket extends Packet {
    private boolean isUnTrunc = false;
    private String unTrunc;
    private String unFull;
    private String pW;

    private ConnectPacket(String pW, String unTrunc, String unFull, boolean isUnTrunc) {
        super((byte) 1, -1L);
        this.pW = pW;
        this.unTrunc = unTrunc;

        if (unFull != null) {
            this.unFull = unTrunc;
        } else {
            this.unFull = unTrunc;
        }

        this.isUnTrunc = isUnTrunc;
    }

    public static ConnectPacket cPFactory(String pW, String unTrunc, String unFull, boolean isUnTrunc) {
        return new ConnectPacket(pW, unTrunc, unFull, isUnTrunc);
    }

    public static ConnectPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        String pWR = dIS.readUTF();
        String unTruncR = dIS.readUTF();
        String unFullR = dIS.readUTF();
        boolean isUnTruncR = dIS.readBoolean();

        if (sentIDR != -1) {
            throw new IllegalPacketException();
        }

        return ConnectPacket.cPFactory(pWR, unTruncR, unFullR, isUnTruncR);
    }

    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeUTF(pW);
        dOS.writeUTF(unTrunc);
        dOS.writeUTF(unFull);
        dOS.writeBoolean(isUnTrunc);
    }

    public boolean isUnTrunc() {
        return isUnTrunc;
    }

    public String getUnTrunc() {
        return unTrunc;
    }

    public String getUnFull() {
        return unFull;
    }

    public String getpW() {
        return pW;
    }
}
