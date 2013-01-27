package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 9
 * @version 0.01
 */
public class ChatPacket extends Packet {

    private String message = null;

    private ChatPacket(long sentID, String message) {
        super((byte) 69, sentID);

        if (message.length() > 160) {
            this.message = "";

            for (int i = 0; i < 160; i++) {
                this.message += message.charAt(i);
            }
        } else {
            this.message = message;
        }
    }

    public static Packet packetFactory(long sentID, String message) {
        return new ChatPacket(sentID, message);
    }

    public static Packet processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        String messageR = dIS.readUTF();

        return ChatPacket.packetFactory(sentIDR, messageR);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeUTF(message);

        dOS.flush();
    }

    public String getMessage() {
        return message;
    }
}
