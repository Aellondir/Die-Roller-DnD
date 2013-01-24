package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 2
 * @version 0.01
 */
public class ConnectionAcceptedPacket extends Packet {

    private ConnectionAcceptedPacket(long sentID) {
        super((byte) 2, sentID);
    }

    public static ConnectionAcceptedPacket packetFactory(long sentID) {
        return new ConnectionAcceptedPacket(sentID);
    }

    public static ConnectionAcceptedPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();

        return ConnectionAcceptedPacket.packetFactory(sentIDR);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.write(packetType);
        dOS.writeLong(sentID);

        dOS.flush();
    }
}
