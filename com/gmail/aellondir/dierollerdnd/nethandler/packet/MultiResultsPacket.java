package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;
import java.util.Iterator;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 4
 * @version 0.01
 */
public class MultiResultsPacket extends Packet {
    private byte[] resultsArr = null;
    private boolean endOfSent = true;

    private byte[] resultsArrRemainder = null;

    private MultiResultsPacket(long sentID, byte[] resultsArr) {
        super((byte) 67, sentID);

        if (resultsArr.length > 191) {
            this.resultsArr = new byte[191];

            System.arraycopy(resultsArr, 0, this.resultsArr, 0, 191);

            resultsArrRemainder = new byte[resultsArr.length - 191];

            System.arraycopy(resultsArr, 191, resultsArrRemainder, 0, resultsArr.length);

            endOfSent = false;
        } else {
            this.resultsArr = resultsArr;
        }
    }

    private MultiResultsPacket(long sentID, byte[] resultsArr, boolean end) {
        this(sentID, resultsArr);

        endOfSent = end;
    }

    public static MultiResultsPacket packetFactory(long sentID, byte[] resultsArr) {
        return new MultiResultsPacket(sentID, resultsArr);
    }

    private static MultiResultsPacket packetFactory(long sentID, byte[] resultsArr, boolean end) {
        return new MultiResultsPacket(sentID, resultsArr, end);
    }

    public static MultiResultsPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        int numBytes = dIS.readInt();

        byte [] resArrR = new byte[numBytes];

        for (int i = 0; i < numBytes; i++) {
            resArrR[i] = dIS.readByte();
        }

        boolean end = dIS.readBoolean();

        return packetFactory(sentIDR, resArrR, end);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeInt(resultsArr.length);

        for (byte b: resultsArr) {
            dOS.writeByte(b);
        }

        dOS.writeBoolean(endOfSent);

        dOS.flush();
    }

    public byte[] getResultsArr() {
        return resultsArr;
    }

    public boolean isEndOfSent() {
        return endOfSent;
    }

    public byte[] getResultsArrRemainder() {
        return resultsArrRemainder;
    }

    private void setEnd(boolean end) {
        endOfSent = end;
    }
}
