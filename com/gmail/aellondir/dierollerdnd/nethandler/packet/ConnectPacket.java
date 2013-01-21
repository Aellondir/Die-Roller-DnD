package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import com.gmail.aellondir.dierollerdnd.nethandler.NetHandler;
import java.io.*;

public class ConnectPacket extends Packet {
    private byte packetSize;
    private boolean isUnTrunc = false;
    private byte[] un;
    private byte[] pW;

    private ConnectPacket(String un, String pW, boolean isUnTrunc, long sentID) {
        this.un = un.getBytes(NetHandler.getCharset());
        this.pW = pW.getBytes(NetHandler.getCharset());
        this.packetType = 1;
        this.sentID = new Long(sentID);
        this.sentID[0] = -1;

        this.isUnTrunc = isUnTrunc;

        //the integer literals (not the calls to the .length value of arrays) respectively represent
        //packetType,
        packetSize = this.sentID.length + 1 + ;
    }

    @Override
    public void processReadPacket(DataInputStream dIS) {
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) {
    }
}
