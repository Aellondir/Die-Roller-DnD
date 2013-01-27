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

    private MultiResultsPacket(long sentID, byte[] resultsArr, boolean end) {
        super((byte) 67, sentID);

        this.resultsArr = resultsArr;

        endOfSent = end;
    }

    public static MultiResultsPacket packetFactory(long sentID, byte[] resultsArr, boolean end) {
        if (resultsArr.length < 191) {
            MultiResultsPacket packet;
            byte[] resultsArrF;
            boolean endI;

            if (resultsArr.length > 191) {
                resultsArrF = new byte[191];

                System.arraycopy(resultsArr, 0, resultsArrF, 0, 191);

                end = false;
            } else {
                resultsArrF = resultsArr;

                end = true;
            }

            packet = new MultiResultsPacket(sentID, resultsArrF, end);

            if (end == false) {
                packet.setResultsArrRemainder(resultsArr.length - 191);

                System.arraycopy(resultsArr, 191, packet.getResultsArrRemainder(), 0, resultsArr.length);
            }

            return packet;
        } else {
            return new MultiResultsPacket(sentID, resultsArr, end);
        }
    }

    public static MultiResultsPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        int numBytes = dIS.readInt();

        byte[] resArrR = new byte[numBytes];

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

        for (byte b : resultsArr) {
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

    private void setResultsArrRemainder(int indices) {
        resultsArrRemainder = new byte[indices];
    }
}
