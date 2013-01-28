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
    private String recipient = null;

    private ChatPacket(long sentID, String message, String recipient) {
        super((byte) 69, sentID);

        if (message.length() > 160) {

            this.message = message.substring(0, 160);
        } else {
            this.message = message;
        }

        this.recipient = recipient;
    }

    public static Packet packetFactory(long sentID, String message, String recipient) {
        return new ChatPacket(sentID, message, recipient);
    }

    public static Packet processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        String messageR = dIS.readUTF();
        String recipient = dIS.readUTF();

        return ChatPacket.packetFactory(sentIDR, messageR, recipient);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);
        dOS.writeUTF(message);
        dOS.writeUTF(recipient);

        dOS.flush();
    }

    public String getMessage() {
        return message;
    }
}
