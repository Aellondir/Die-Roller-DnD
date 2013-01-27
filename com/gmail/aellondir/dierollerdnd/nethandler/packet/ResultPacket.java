package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 6
 * @version 0.01
 */
public class ResultPacket extends Packet {

    private byte result = 0;

    private ResultPacket(long sentID, byte result) {
        super((byte) 66, sentID);

        this.result = result;
    }

    public static Packet packetFactory(long sentID, byte result) {
        return new ResultPacket(sentID, result);
    }

    public byte getResult() {
        return result;
    }

    public static Packet processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        byte resultR = dIS.readByte();

        return ResultPacket.packetFactory(sentIDR, resultR);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeByte(result);
    }
}
