package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;
import java.util.TreeSet;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 8
 * @version 0.01
 */
public class PlayerUNsPacket extends Packet {
    private TreeSet<String> usernamesAlpha;

    private PlayerUNsPacket(long sentID, TreeSet<String> usernamesAlpha) {
        super((byte) 64, sentID);

        this.usernamesAlpha = usernamesAlpha;
    }

    public static PlayerUNsPacket packetFactory(long sentID, TreeSet<String> usernamesAlpha) {
        if (usernamesAlpha.isEmpty()) {
            throw new IllegalArgumentException("The usernamesAlpha variable was empty this should not happen.");
        }

        return new PlayerUNsPacket(sentID, usernamesAlpha);
    }

    public TreeSet<String> getUsernamesAlpha() {
        return usernamesAlpha;
    }

    public static PlayerUNsPacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        int numNamesR = dIS.readInt();
        TreeSet<String> usernamesAlphaR = new TreeSet<>();

        for (int i = 0; i < numNamesR; i++) {
            String str = dIS.readUTF();

            usernamesAlphaR.add(str);
        }

        int nameCheck = dIS.readInt();

        if (nameCheck != 0 && numNamesR == usernamesAlphaR.size()) {
            //@todo Maybe request a resend.
        }

        return PlayerUNsPacket.packetFactory(sentIDR, usernamesAlphaR);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.write(packetType);
        dOS.writeLong(sentID);
        dOS.writeInt(usernamesAlpha.size());

        int i = 0;

        for (String str: usernamesAlpha) {
            dOS.writeUTF(str);

            i++;
        }

        if (i != usernamesAlpha.size()) {
            dOS.writeByte(1);
        } else {
            dOS.write(0);
        }

        dOS.flush();
    }
}
