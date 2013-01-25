package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import com.gmail.aellondir.dierollerdnd.enumerations.DiceDefinitions;
import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 7
 * @version 0.01
 */
public class RollDicePacket extends Packet {
    private short numTRoll = 0;
    private DiceDefinitions dTR = DiceDefinitions.D100;

    public RollDicePacket(long sentID, short numTRoll, DiceDefinitions dTR) {
        super((byte) 65, sentID);

        this.numTRoll = numTRoll;
        this.dTR = dTR;
    }

    public static RollDicePacket packetFactory() {
        //@todo Implementation
    }

    public static RollDicePacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        short numTRoll = dIS.readShort();


    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);

        dOS.writeShort(numTRoll);
        dOS.writeInt(dTR.ordinal());

        dOS.flush();
    }
}
