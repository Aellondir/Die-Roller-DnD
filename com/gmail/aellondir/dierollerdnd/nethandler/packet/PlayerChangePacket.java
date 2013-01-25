package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

public class PlayerChangePacket extends Packet {
     private int playerIndex = 0;
     private String un = null;
     private boolean remove = false;

    private PlayerChangePacket(long sentID, int playerIndex, String un) {
        super((byte) 71, sentID);

        if (un == null) {
            remove = true;
        }

        this.playerIndex = playerIndex;
        this.un = un;
    }

    public static PlayerChangePacket packetFactory(long sentID, int playerIndex, String un) {
        return new PlayerChangePacket(sentID, playerIndex, un);
    }

    public static PlayerChangePacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        String unR = null;
        byte playerIndexR = 0;

        if (dIS.readBoolean()) {
            unR = dIS.readUTF();
        }

        playerIndexR = dIS.readByte();

        return PlayerChangePacket.packetFactory(sentIDR, playerIndexR, unR);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);

        if (un == null) {
            dOS.writeBoolean(false);
        } else {
            dOS.writeBoolean(true);

            dOS.writeUTF(un);
        }

        dOS.writeByte(playerIndex);

        dOS.flush();
    }
}
