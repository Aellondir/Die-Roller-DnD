package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 3
 * @version 0.01
 */
public class ConnectionDeniedPacket extends Packet {

    private final String[] REASON_ARRAY = {"Because fuck you thats why!", "Y U NO ENTER CORRECT PASSWORD", "The GM dislikes you."};
    private int index = 0;

    private ConnectionDeniedPacket(long sentID, int reason) {
        super((byte) 2, sentID);
        this.index = reason;
    }

    public static ConnectionDeniedPacket packetFactory(long sentID, int reason) {
        return new ConnectionDeniedPacket(sentID, reason);
    }

    public static ConnectionDeniedPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        int reasonR = dIS.readInt();

        return ConnectionDeniedPacket.packetFactory(sentIDR, reasonR);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);

        dOS.flush();
    }

    public String getReason() {
        return REASON_ARRAY[index];
    }
}
